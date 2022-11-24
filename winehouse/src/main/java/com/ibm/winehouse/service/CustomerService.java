package com.ibm.winehouse.service;

import com.ibm.winehouse.dto.CustomerDTO;
import com.ibm.winehouse.dto.OrderDTO;
import com.ibm.winehouse.dto.OrderItemDTO;
import com.ibm.winehouse.response.CustomerApiResponse;
import com.ibm.winehouse.rest.MockClient;
import com.ibm.winehouse.rest.response.CustomerResponse;
import com.ibm.winehouse.rest.response.OrderResponse;
import com.ibm.winehouse.service.adapter.AdapterCustomerResponseToCustomerDTO;
import com.ibm.winehouse.util.CustomComparator;
import com.ibm.winehouse.validation.CustomerDTOValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private MockClient mockClient;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerDTOValidation customerDTOValidation;

    @Autowired
    private AdapterCustomerResponseToCustomerDTO adapterCustomerResponseToCustomerDTO;

    public List<CustomerResponse> findLoyalCustomers() {
        List<CustomerDTO> customerDTOList = getCustomerWithOrder();
        List<CustomerDTO> validCostumers = customerDTOValidation.validCustomer(customerDTOList);

        return getLoyalCustomers(validCostumers);
    }

    public List<CustomerResponse> getLoyalCustomers(List<CustomerDTO> customerDTOList) {
        customerDTOList.sort(new CustomComparator());

        int size = Math.min(customerDTOList.size(), 3);
        List<CustomerDTO> customerTopList = customerDTOList.subList(0, size);

        return customerTopList.stream()
                .map(CustomerResponse::new)
                .collect(Collectors.toList());
    }

    public List<CustomerDTO> getCustomerWithOrder() {
        List<CustomerResponse> clientResponseList = mockClient.getClientList();
        List<OrderResponse> orderResponseList = orderService.findAllOrders();

        return adapterCustomerResponseToCustomerDTO
                .adapter(clientResponseList, orderResponseList);
    }

    public CustomerApiResponse findRecommendationWine(Long id) {

        List<CustomerDTO> customerDTOList = customerDTOValidation
                .validCustomer(getCustomerWithOrder());

        CustomerDTO customerDTO = findCustomerById(id, customerDTOList);

        if (customerDTO == null) return null;

        return getRecommendation(customerDTO);
    }

    public CustomerDTO findCustomerById(Long id, List<CustomerDTO> customerDTOList) {
        return customerDTOList
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public CustomerApiResponse getRecommendation(CustomerDTO customerDTO) {
        Map<String, Integer> coutCategory = new HashMap<>();

        for (OrderDTO orderDTO : customerDTO.getOrders()) {

            for (OrderItemDTO orderItemDTO : orderDTO.getItems()) {

                String category = orderItemDTO.getCategory();
                Integer count = coutCategory.get(category);
                coutCategory.put(category, count == null ? 1 : count + 1);
            }
        }
        String recommendation = coutCategory.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .map(Map.Entry::getKey)
                .findFirst().orElse(null);

        return CustomerApiResponse.builder()
                .recommendation(recommendation)
                .name(customerDTO.getName())
                .id(customerDTO.getId())
                .build();
    }
}
