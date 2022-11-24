package com.ibm.purchases.service;

import com.ibm.purchases.dto.CustomerDTO;
import com.ibm.purchases.dto.ShippingDTO;
import com.ibm.purchases.dto.ShippingItemDTO;
import com.ibm.purchases.response.CustomerApiResponse;
import com.ibm.purchases.rest.MockClient;
import com.ibm.purchases.rest.response.CustomerResponse;
import com.ibm.purchases.rest.response.ShippingResponse;
import com.ibm.purchases.service.adapter.AdapterCustomerResponseToCustomerDTO;
import com.ibm.purchases.util.CustomComparator;
import com.ibm.purchases.validation.CustomerDTOValidation;
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
    private ShippingService shippingService;

    @Autowired
    private CustomerDTOValidation customerDTOValidation;

    @Autowired
    private AdapterCustomerResponseToCustomerDTO adapterCustomerResponseToCustomerDTO;

    public List<CustomerResponse> findLoyalCustomers() {
        List<CustomerDTO> customerDTOList = getCustomerWithPurchase();
        List<CustomerDTO> validCostumers = customerDTOValidation.validCustomer(customerDTOList);

        return getLoyalCustomers(validCostumers);
    }

    public List<CustomerResponse> getLoyalCustomers(List<CustomerDTO> customerDTOList) {

        customerDTOList.sort(new CustomComparator());
        List<CustomerDTO> customerTopList = customerDTOList.subList(0, 3);

        return customerTopList.stream()
                .map(CustomerResponse::new)
                .collect(Collectors.toList());
    }

    public List<CustomerDTO> getCustomerWithPurchase() {
        List<CustomerResponse> clientList = mockClient.getClientList();
        List<ShippingResponse> shippingResponses = shippingService.findAllShipping();

        return adapterCustomerResponseToCustomerDTO
                .adapter(clientList, shippingResponses);
    }

    public CustomerApiResponse findRecommendationByWine(Long id) {

        List<CustomerDTO> customerDTOList = customerDTOValidation
                .validCustomer(getCustomerWithPurchase());

        CustomerDTO customerDTO = findCustomerById(id, customerDTOList);

        if( customerDTO == null) return null;

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

        for (ShippingDTO shippingDTO : customerDTO.getPurchases()) {

            for (ShippingItemDTO shippingItemDTO : shippingDTO.getItems()) {

                String category = shippingItemDTO.getCategory();
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
