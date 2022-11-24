package com.ibm.winehouse.builder;

import com.ibm.winehouse.dto.OrderItemDTO;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class OrderItemDTOBuilder {

    private OrderItemDTO orderItemDTO;

    public static OrderItemDTOBuilder shippingItemDTODefault() {
        OrderItemDTOBuilder builder = new OrderItemDTOBuilder();
        builder.orderItemDTO = new OrderItemDTO().builder().category("Tinto").code("e9eee4af-13d0-4da1-9a91-36b100e1c98e").country("Brasil").harvest("2013").price(BigDecimal.valueOf(55)).product("Casa Valduga Raízes").variety("Merlot").build();
        return builder;
    }

    public List<OrderItemDTO> toList() {
        return Collections.singletonList(orderItemDTO);
    }

    public OrderItemDTO build() {
        return orderItemDTO;
    }
}
