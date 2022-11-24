package com.ibm.purchases.rest.response;

import com.ibm.purchases.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

    private Long id;
    private String nome;
    private String cpf;

    public CustomerResponse(CustomerDTO customerDTO) {
        this.id = customerDTO.getId();
        this.nome = customerDTO.getName();
        this.cpf = customerDTO.getDocument();
    }
}
