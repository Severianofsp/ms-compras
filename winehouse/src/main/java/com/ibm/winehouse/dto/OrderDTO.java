package com.ibm.winehouse.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private String code;
    private LocalDate date;
    private List<OrderItemDTO> items;
    private BigDecimal total;
}
