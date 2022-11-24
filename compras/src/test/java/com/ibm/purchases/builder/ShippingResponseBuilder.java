package com.ibm.purchases.builder;


import com.ibm.purchases.rest.response.ShippingResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static com.ibm.purchases.builder.ShippingItemResponseBuilder.shippingItemResponseBuilderDefault;

public class ShippingResponseBuilder {

    private ShippingResponse shippingResponse;

    public static ShippingResponseBuilder shippingResponseDefault() {

        ShippingResponseBuilder builder = new ShippingResponseBuilder();
        builder.shippingResponse = ShippingResponse.builder()
                .code("b25433d4-366d-4cc7-8e21-2f6d9a4b8b51")
                .client("000.000.000-04")
                .date(LocalDate.of(2022, 11, 24))
                .total(BigDecimal.valueOf(208))
                .build();

        return builder;
    }

    public ShippingResponseBuilder withItems() {
        shippingResponse
                .setItems(shippingItemResponseBuilderDefault()
                        .shippingItemResponseList());
        return this;
    }

    public ShippingResponse build() {
        return shippingResponse;
    }

    public List<ShippingResponse> toList() {
        return Collections.singletonList(shippingResponse);
    }
}
