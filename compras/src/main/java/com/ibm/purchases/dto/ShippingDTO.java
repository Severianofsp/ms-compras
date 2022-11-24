package com.ibm.purchases.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ShippingDTO {

    private String code;
    private LocalDate date;
    private List<ShippingItemDTO> items;
    private BigDecimal total;
}
