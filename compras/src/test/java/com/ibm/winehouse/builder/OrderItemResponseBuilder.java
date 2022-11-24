package com.ibm.winehouse.builder;

import com.ibm.winehouse.rest.response.OrderItemResponse;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class OrderItemResponseBuilder {

    private OrderItemResponse orderItemResponse;

    public static OrderItemResponseBuilder shippingItemResponseBuilderDefault() {
        OrderItemResponseBuilder builder = new OrderItemResponseBuilder();
        builder.orderItemResponse = OrderItemResponse.builder()
                .category("Tinto")
                .code("e9eee4af-13d0-4da1-9a91-36b100e1c98e")
                .country("Brasil")
                .harvest("2013")
                .price(BigDecimal.valueOf(55))
                .product("Casa Valduga Raízes")
                .variety("Merlot")
                .build();
        return builder;
    }

    public List<OrderItemResponse> shippingItemResponseList() {
        OrderItemResponse orderItemResponseBuild = OrderItemResponse.builder()
                .category("Tinto")
                .code("8c76af8e-cde8-45aa-8aae-14e040080371")
                .country("Brasil")
                .harvest("2009")
                .price(BigDecimal.valueOf(110))
                .product("Casa Valduga Gran Reserva")
                .variety("Chardonnay")
                .build();

        return Arrays.asList(orderItemResponseBuild, orderItemResponse);
    }

}