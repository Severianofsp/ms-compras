package com.ibm.purchases.service.adapter;

import com.ibm.purchases.dto.CustomerDTO;
import com.ibm.purchases.rest.response.CustomerResponse;
import com.ibm.purchases.rest.response.ShippingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdapterCustomerResponseToCustomerDTO {

    @Autowired
    private AdapterShippingResponseToShippingDTO adapterShippingResponseToShippingDTO;

    public List<CustomerDTO> adapter(List<CustomerResponse> customerResponseList, List<ShippingResponse> shippingResponseList) {
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        for (CustomerResponse customerResponse : customerResponseList) {
            CustomerDTO customerDTO = CustomerDTO.builder()
                    .id(customerResponse.getId())
                    .document(customerResponse.getDocument())
                    .name(customerResponse.getName())
                    .purchases(adapterShippingResponseToShippingDTO.adapter(customerResponse, shippingResponseList))
                    .build();
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }
}
