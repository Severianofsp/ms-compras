package com.ibm.purchases.builder;

import com.ibm.purchases.rest.response.CustomerResponse;

import java.util.Collections;
import java.util.List;

public class CustomerResponseBuilder {

    private CustomerResponse customerResponse;

    public static CustomerResponseBuilder customerResponseDefault() {
        CustomerResponseBuilder builder = new CustomerResponseBuilder();
        builder.customerResponse = CustomerResponse.builder()
                .document("000.000.000-04")
                .name("Gustavo")
                .id(4L)
                .build();
        return builder;
    }


    public CustomerResponse build() {
        return customerResponse;
    }

    public List<CustomerResponse> toList() {
        return Collections.singletonList(customerResponse);
    }
}
