package com.ibm.purchases.builder;

import com.ibm.purchases.dto.ShippingDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static com.ibm.purchases.builder.ShippingItemDTOBuilder.shippingItemDTODefault;

public class ShippingDTOBuilder {

    private ShippingDTO shippingDTO;

    public static ShippingDTOBuilder shippingResponseDefault() {

        ShippingDTOBuilder builder = new ShippingDTOBuilder();
        builder.shippingDTO = ShippingDTO.builder()
                .code("b25433d4-366d-4cc7-8e21-2f6d9a4b8b51")
                .date(LocalDate.of(2022, 11, 24))
                .total(BigDecimal.valueOf(208))
                .build();

        return builder;
    }

    public ShippingDTOBuilder withItem() {

        shippingDTO.setItems(shippingItemDTODefault().toList());
        return this;
    }

    public ShippingDTO build() {
        return shippingDTO;
    }

    public List<ShippingDTO> toList() {
        return Collections.singletonList(shippingDTO);
    }
}
