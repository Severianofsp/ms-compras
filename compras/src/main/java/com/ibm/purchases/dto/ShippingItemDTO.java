package com.ibm.purchases.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ShippingItemDTO {

    private String code;
    private String product;
    private String variety;
    private String country;
    private String category;
    private String harvest;
    private BigDecimal price;
}
