package com.ibm.winehouse.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerApiResponse {
    private Long id;

    @JsonProperty("nome")
    private String name;

    @JsonProperty("recomendacao")
    private String recommendation;
}
