package com.ibm.purchases.builder;

import com.ibm.purchases.rest.response.ShippingItemResponse;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ShippingItemResponseBuilder {

    private ShippingItemResponse shippingItemResponse;

    public static ShippingItemResponseBuilder shippingItemResponseBuilderDefault() {
        ShippingItemResponseBuilder builder = new ShippingItemResponseBuilder();
        builder.shippingItemResponse = ShippingItemResponse.builder()
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

    public List<ShippingItemResponse> shippingItemResponseList() {
        ShippingItemResponse shippingItemResponseBuild = ShippingItemResponse.builder()
                .category("Tinto")
                .code("8c76af8e-cde8-45aa-8aae-14e040080371")
                .country("Brasil")
                .harvest("2009")
                .price(BigDecimal.valueOf(110))
                .product("Casa Valduga Gran Reserva")
                .variety("Chardonnay")
                .build();

        return Arrays.asList(shippingItemResponseBuild, shippingItemResponse);
    }

    public ShippingItemResponse build() {
        return shippingItemResponse;
    }
}