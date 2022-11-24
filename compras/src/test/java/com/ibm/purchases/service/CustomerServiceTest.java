package com.ibm.purchases.service;

import com.ibm.purchases.builder.CustomerDTOBuilder;
import com.ibm.purchases.builder.CustomerResponseBuilder;
import com.ibm.purchases.builder.ShippingResponseBuilder;
import com.ibm.purchases.dto.CustomerDTO;
import com.ibm.purchases.response.CustomerApiResponse;
import com.ibm.purchases.rest.MockClient;
import com.ibm.purchases.rest.response.CustomerResponse;
import com.ibm.purchases.rest.response.ShippingResponse;
import com.ibm.purchases.service.adapter.AdapterCustomerResponseToCustomerDTO;
import com.ibm.purchases.validation.CustomerDTOValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.ibm.purchases.builder.CustomerDTOBuilder.*;
import static com.ibm.purchases.builder.CustomerResponseBuilder.*;
import static com.ibm.purchases.builder.ShippingResponseBuilder.*;
import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private MockClient mockClient;

    @Mock
    private ShippingService shippingService;

    @Mock
    private AdapterCustomerResponseToCustomerDTO adapterCustomerResponseToCustomerDTO;

    @Mock
    private CustomerDTOValidation customerDTOValidation;

    @Test
    @DisplayName("Should return loyals customers")
    void shouldReturnLoyalsCustomers(){
        //Data
        List<CustomerDTO> customerDTO = singletonList(customerDTODefault().withPurchase().build());
        List<CustomerResponse> customerResponse = customerResponseDefault().toList();
        List<ShippingResponse> shippingResponse = shippingResponseDefault().withItems().toList();

        when(mockClient.getClientList()).thenReturn(customerResponse);
        when(shippingService.findAllPurchases()).thenReturn(shippingResponse);
        when(adapterCustomerResponseToCustomerDTO.adapter(customerResponse,shippingResponse)).thenReturn(customerDTO);
        when(customerDTOValidation.validCustomer(customerDTO)).thenReturn(customerDTO);

        //Action
        List<CustomerResponse> loyalCustomers = customerService
                .findLoyalCustomers();

        //Result
        assertEquals("Gustavo", loyalCustomers.get(0).getName());
        assertEquals(4L, loyalCustomers.get(0).getId());
        assertEquals("000.000.000-04", loyalCustomers.get(0).getDocument());
    }

    @Test
    @DisplayName("Should return customer with purchase")
    void shouldReturnCustomerWithPurchase(){
        //Data
        List<CustomerDTO> customerDTO = singletonList(customerDTODefault().withPurchase().build());
        List<CustomerResponse> customerResponse = customerResponseDefault().toList();
        List<ShippingResponse> shippingResponse = shippingResponseDefault().withItems().toList();

        when(mockClient.getClientList()).thenReturn(customerResponse);
        when(shippingService.findAllPurchases()).thenReturn(shippingResponse);
        when(adapterCustomerResponseToCustomerDTO.adapter(customerResponse,shippingResponse)).thenReturn(customerDTO);

        //Action
        List<CustomerDTO> customerWithPurchase = customerService
                .getCustomerWithPurchase();

        //Result
        assertEquals("Gustavo", customerWithPurchase.get(0).getName());
        assertEquals(4L, customerWithPurchase.get(0).getId());
        assertEquals("000.000.000-04", customerWithPurchase.get(0).getDocument());
        assertNotEquals(0,customerWithPurchase.get(0).getPurchases().size());
    }

    @Test
    @DisplayName("Should return recommendation wine when send id")
    void shouldReturnRecommendationWineWhenSendId() {
        //Data
        List<CustomerDTO> customerDTO = singletonList(customerDTODefault().withPurchase().build());
        List<CustomerResponse> customerResponse = customerResponseDefault().toList();
        List<ShippingResponse> shippingResponse = shippingResponseDefault().withItems().toList();

        when(mockClient.getClientList()).thenReturn(customerResponse);
        when(shippingService.findAllPurchases()).thenReturn(shippingResponse);
        when(adapterCustomerResponseToCustomerDTO.adapter(customerResponse,shippingResponse)).thenReturn(customerDTO);
        when(customerDTOValidation.validCustomer(customerDTO)).thenReturn(customerDTO);

        //Action
        CustomerApiResponse recommendationWine = customerService
                .findRecommendationWine(4L);

        //Result
        assertEquals("Gustavo", recommendationWine.getName());
        assertEquals(4L, recommendationWine.getId());
        assertEquals("Tinto", recommendationWine.getRecommendation());
    }

    @Test
    @DisplayName("Should return null when send nonexistent id")
    void shouldReturnNullWhenSendNonexistentId() {

        //Action
        CustomerApiResponse recommendationWine = customerService
                .findRecommendationWine(5L);

        //Result
        assertNull(recommendationWine);
    }

    @Test
    @DisplayName("Should return customer when send id")
    void shouldReturnCustomerWhenSendId() {
        //Data
        CustomerDTO customerDTO = customerDTODefault().withPurchase().build();

        //Action
        CustomerDTO customerById = customerService
                .findCustomerById(4L, singletonList(customerDTO));

        //Result
        assertEquals("Gustavo", customerById.getName());
        assertEquals(4L, customerById.getId());
        assertEquals("000.000.000-04", customerById.getDocument());
    }

    @Test
    @DisplayName("Should return null when send inexistent id")
    void shouldReturnNullWhenSendInexistentId() {
        //Data
        CustomerDTO customerDTO = customerDTODefault().withPurchase().build();

        //Action
        CustomerDTO customerById = customerService
                .findCustomerById(5L, singletonList(customerDTO));

        //Result
        assertNull(customerById);
    }

    @Test
    @DisplayName("Should return recommendation when send customer dto")
    void shouldReturnRecommendationWhenSendCustomerDTO() {

        //Action
        CustomerApiResponse recommendation = customerService
                .getRecommendation(customerDTODefault().withPurchase().build());

        //Result
        assertEquals("Gustavo", recommendation.getName());
        assertEquals(4L, recommendation.getId());
        assertEquals("Tinto", recommendation.getRecommendation());
    }
}
