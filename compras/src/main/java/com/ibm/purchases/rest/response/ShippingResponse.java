package com.ibm.purchases.rest.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ibm.purchases.validation.ShippingResponseValidation;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@Getter
@Setter
@ShippingResponseValidation
public class ShippingResponse implements Comparable<ShippingResponse> {

    @JsonProperty("codigo")
    @NotNull
    private String code;

    @JsonProperty("data")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Calendar date;

    @JsonProperty("cliente")
    private String client;

    @JsonProperty("itens")
    private List<ShippingItem> items;

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
