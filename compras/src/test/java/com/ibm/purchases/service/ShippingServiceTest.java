package com.ibm.purchases.service;

import com.ibm.purchases.rest.MockClient;
import com.ibm.purchases.rest.response.ShippingResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static com.ibm.purchases.builder.ShippingResponseBuilder.shippingResponseDefault;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class ShippingServiceTest {

    @InjectMocks
    private ShippingService shippingService;

    @Mock
    private MockClient mockClient;

    @Test
    @DisplayName("Should return all purchases")
    void shouldReturnAllPurchases() {

        //Data
        when(mockClient.getShippingList()).thenReturn(shippingResponseDefault().toList());
        // Action
        List<ShippingResponse> purchases = shippingService.findAllPurchases();

        //Result
        assertNotNull(purchases);
    }

    @Test
    @DisplayName("Should return biggest buy when send year")
    void shouldReturnBiggestBuyWhenSendYear(){

        //Data
        when(mockClient.getShippingList()).thenReturn(shippingResponseDefault().toList());

        // Action
        ShippingResponse biggestBuy = shippingService.findBiggestBuy(2022);

        //Result
        assertNotNull(biggestBuy);
        assertEquals(2022,biggestBuy.getDate().getYear());
        assertEquals("b25433d4-366d-4cc7-8e21-2f6d9a4b8b51",biggestBuy.getCode());
        assertEquals("000.000.000-04",biggestBuy.getClient());
    }
}
