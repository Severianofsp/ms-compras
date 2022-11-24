package com.ibm.winehouse.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

    private String code;
    private String product;
    private String variety;
    private String country;
    private String category;
    private String harvest;
    private BigDecimal price;

}
