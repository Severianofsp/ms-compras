package com.ibm.purchases.rest.response;

import com.ibm.purchases.dto.CustomerDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {

    private Long id;
    private String name;
    private String document;

    public CustomerResponse(CustomerDTO customerDTO) {
        this.id = customerDTO.getId();
        this.name = customerDTO.getName();
        this.document = customerDTO.getDocument();
    }
}
