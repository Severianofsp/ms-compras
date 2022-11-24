package com.ibm.winehouse.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CustomerControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Given loyalt uri when MockMvc then return top three customers")
    public void givenLoyaltCustomerURIWhenMockMvcThenReturnTopThreeCustomers() throws Exception {
        this.mockMvc.perform(get("/clientes-fieis")
                        .contentType(APPLICATION_JSON))
                //Result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Vinicius"))
                .andExpect(jsonPath("$[0].cpf").value("000.000.000-01"))
                .andExpect(jsonPath("$[1].nome").value("Pamela"))
                .andExpect(jsonPath("$[1].cpf").value("000.000.000-06"))
                .andExpect(jsonPath("$[2].nome").value("Bruno"))
                .andExpect(jsonPath("$[2].cpf").value("000.000.000-07"));


    }

    @Test
    @DisplayName("Given recommendation URI when MockMvc then return customer recommendation")
    public void givenOrderURIWIthYearWhenMockMvcThenReturnBiggestOrder() throws Exception {
        this.mockMvc.perform(get("/recomendacao/7/tipo")
                        .contentType(APPLICATION_JSON))
                //Result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Bruno"))
                .andExpect(jsonPath("$.recomendacao").value("Tinto"));
    }

}
