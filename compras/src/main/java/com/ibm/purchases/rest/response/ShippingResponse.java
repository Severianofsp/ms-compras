package com.ibm.purchases.rest.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ibm.purchases.validation.ShippingResponseValidation;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ShippingResponseValidation
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShippingResponse implements Comparable<ShippingResponse> {

    @JsonProperty("codigo")
    @NotNull
    private String code;

    @JsonProperty("data")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    @JsonProperty("cliente")
    private String client;

    @JsonProperty("itens")
    private List<ShippingItemResponse> items;

    @JsonProperty("valorTotal")
    private BigDecimal total;

    @Override
    public int compareTo(ShippingResponse o) {

        if (getTotal() == null || o.getTotal() == null) {
            return 0;
        }
        return getTotal().compareTo(o.getTotal());
    }

}
