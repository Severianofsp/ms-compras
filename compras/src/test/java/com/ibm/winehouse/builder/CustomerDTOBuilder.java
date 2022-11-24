package com.ibm.winehouse.builder;

import com.ibm.winehouse.dto.CustomerDTO;

import static com.ibm.winehouse.builder.OrderDTOBuilder.*;

public class CustomerDTOBuilder {

    private CustomerDTO customerDTO;

    public static CustomerDTOBuilder customerDTODefault() {
        CustomerDTOBuilder builder = new CustomerDTOBuilder();
        builder.customerDTO = CustomerDTO.builder()
                .document("000.000.000-04")
                .name("Gustavo")
                .id(4L)
                .build();
        return builder;
    }

    public CustomerDTOBuilder withPurchase(){

        customerDTO.setOrders(shippingResponseDefault().withItem().toList());
        return this;
    }

    public CustomerDTO build() {
        return customerDTO;
    }
}
