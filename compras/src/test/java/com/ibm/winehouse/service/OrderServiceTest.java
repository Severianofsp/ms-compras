package com.ibm.winehouse.service;

import com.ibm.winehouse.rest.MockClient;
import com.ibm.winehouse.rest.response.OrderResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static com.ibm.winehouse.builder.OrderResponseBuilder.shippingResponseDefault;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private MockClient mockClient;

    @Test
    @DisplayName("Should return all purchases")
    void shouldReturnAllPurchases() {

        //Data
        when(mockClient.getOrderList()).thenReturn(shippingResponseDefault().toList());
        // Action
        List<OrderResponse> purchases = orderService.findAllOrders();

        //Result
        assertNotNull(purchases);
    }

    @Test
    @DisplayName("Should return biggest buy when send year")
    void shouldReturnBiggestBuyWhenSendYear(){

        //Data
        when(mockClient.getOrderList()).thenReturn(shippingResponseDefault().toList());

        // Action
        OrderResponse biggestBuy = orderService.findBiggestOrder(2022);

        //Result
        assertNotNull(biggestBuy);
        assertEquals(2022,biggestBuy.getDate().getYear());
        assertEquals("b25433d4-366d-4cc7-8e21-2f6d9a4b8b51",biggestBuy.getCode());
        assertEquals("000.000.000-04",biggestBuy.getClient());
    }
}
