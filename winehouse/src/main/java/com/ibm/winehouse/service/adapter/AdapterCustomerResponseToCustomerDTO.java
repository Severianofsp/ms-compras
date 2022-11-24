package com.ibm.winehouse.service.adapter;

import com.ibm.winehouse.dto.CustomerDTO;
import com.ibm.winehouse.rest.response.CustomerResponse;
import com.ibm.winehouse.rest.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdapterCustomerResponseToCustomerDTO {

    @Autowired
    private AdapterOrderResponseToOrderDTO adapterOrderResponseToOrderDTO;

    public List<CustomerDTO> adapter(List<CustomerResponse> customerResponseList, List<OrderResponse> orderResponseList) {
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        for (CustomerResponse customerResponse : customerResponseList) {
            CustomerDTO customerDTO = CustomerDTO.builder()
                    .id(customerResponse.getId())
                    .document(customerResponse.getDocument())
                    .name(customerResponse.getName())
                    .orders(adapterOrderResponseToOrderDTO.adapter(customerResponse, orderResponseList))
                    .build();
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }
}
