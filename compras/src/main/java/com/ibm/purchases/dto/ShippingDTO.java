package com.ibm.purchases.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ShippingDTO {

    private String code;
    private Calendar date;
    private List<ShippingItemDTO> items;
    private BigDecimal total;
}
