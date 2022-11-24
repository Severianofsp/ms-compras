package com.ibm.winehouse.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class OrderItemResponse {
    @JsonProperty("codigo")
    private String code;

    @JsonProperty("produto")
    private String product;

    @JsonProperty("variedade")
    private String variety;

    @JsonProperty("pais")
    private String country;

    @JsonProperty("categoria")
    private String category;

    @JsonProperty("safra")
    private String harvest;

    @JsonProperty("preco")
    private BigDecimal price;
}
