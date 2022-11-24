package com.ibm.winehouse.builder;

import com.ibm.winehouse.dto.OrderDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static com.ibm.winehouse.builder.OrderItemDTOBuilder.shippingItemDTODefault;

public class OrderDTOBuilder {

    private OrderDTO orderDTO;

    public static OrderDTOBuilder shippingResponseDefault() {

        OrderDTOBuilder builder = new OrderDTOBuilder();
        builder.orderDTO = OrderDTO.builder()
                .code("b25433d4-366d-4cc7-8e21-2f6d9a4b8b51")
                .date(LocalDate.of(2022, 11, 24))
                .total(BigDecimal.valueOf(208))
                .build();

        return builder;
    }

    public OrderDTOBuilder withItem() {

        orderDTO.setItems(shippingItemDTODefault().toList());
        return this;
    }


    public List<OrderDTO> toList() {
        return Collections.singletonList(orderDTO);
    }
}
