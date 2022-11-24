package com.ibm.winehouse.builder;


import com.ibm.winehouse.rest.response.OrderResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static com.ibm.winehouse.builder.OrderItemResponseBuilder.shippingItemResponseBuilderDefault;

public class OrderResponseBuilder {

    private OrderResponse orderResponse;

    public static OrderResponseBuilder shippingResponseDefault() {

        OrderResponseBuilder builder = new OrderResponseBuilder();
        builder.orderResponse = OrderResponse.builder()
                .code("b25433d4-366d-4cc7-8e21-2f6d9a4b8b51")
                .client("000.000.000-04")
                .date(LocalDate.of(2022, 11, 24))
                .total(BigDecimal.valueOf(208))
                .build();

        return builder;
    }

    public OrderResponseBuilder withItems() {
        orderResponse
                .setItems(shippingItemResponseBuilderDefault()
                        .shippingItemResponseList());
        return this;
    }

    public List<OrderResponse> toList() {
        return Collections.singletonList(orderResponse);
    }
}
