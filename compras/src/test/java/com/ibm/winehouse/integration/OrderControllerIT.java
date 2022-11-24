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
public class OrderControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Given order uri when MockMvc then return sorted order list")
    public void givenOrderURIWhenMockMvcThenReturnSortedOrderList() throws Exception {
        this.mockMvc.perform(get("/compras")
                        .contentType(APPLICATION_JSON))
                //Result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cliente").value("000.000.000-08"))
                .andExpect(jsonPath("$[0].codigo").value("8d38d75f-e612-4d65-b597-2ff92b574a88"))
                .andExpect(jsonPath("$[0].data").value("18-07-2016"))
                .andExpect(jsonPath("$[0].itens[0].codigo").value("e9eee4af-13d0-4da1-9a91-36b100e1c98e"));


    }

    @Test
    @DisplayName("Given order URI with year when MockMvc then return biggest order")
    public void givenOrderURIWIthYearWhenMockMvcThenReturnBiggestOrder() throws Exception {
        this.mockMvc.perform(get("/maior-compra/2015")
                        .contentType(APPLICATION_JSON))
                //Result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cliente").value("000.000.000-04"))
                .andExpect(jsonPath("$.codigo").value("7405ad98-ca99-4fd3-994f-85be49633141"))
                .andExpect(jsonPath("$.data").value("18-05-2015"))
                .andExpect(jsonPath("$.itens[0].codigo").value("449cb4e2-67d1-4087-9192-4ac6bd018727"));

    }

}
