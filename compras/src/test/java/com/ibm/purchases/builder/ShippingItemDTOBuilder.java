package com.ibm.purchases.builder;

import com.ibm.purchases.dto.ShippingItemDTO;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class ShippingItemDTOBuilder {

    private ShippingItemDTO shippingItemDTO;

    public static ShippingItemDTOBuilder shippingItemDTODefault() {
        ShippingItemDTOBuilder builder = new ShippingItemDTOBuilder();
        builder.shippingItemDTO = new ShippingItemDTO().builder()
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

    public List<ShippingItemDTO> toList() {
        return Collections.singletonList(shippingItemDTO);
    }

    public ShippingItemDTO build() {
        return shippingItemDTO;
    }
}
