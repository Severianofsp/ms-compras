package com.ibm.winehouse.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ibm.winehouse.dto.CustomerDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {

    private Long id;
    @JsonProperty("nome")
    private String name;

    @JsonProperty("cpf")
    private String document;

    public CustomerResponse(CustomerDTO customerDTO) {
        this.id = customerDTO.getId();
        this.name = customerDTO.getName();
        this.document = customerDTO.getDocument();
    }
}
