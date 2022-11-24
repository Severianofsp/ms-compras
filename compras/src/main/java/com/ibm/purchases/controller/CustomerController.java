package com.ibm.purchases.controller;

import com.ibm.purchases.response.CustomerApiResponse;
import com.ibm.purchases.rest.response.CustomerResponse;
import com.ibm.purchases.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/clientes-fieis")
    private ResponseEntity<List<CustomerResponse>> findLoyalCustomers() {
        List<CustomerResponse> loyalCustomers = customerService.findLoyalCustomers();

        return ResponseEntity.ok().body(loyalCustomers);
    }

    @GetMapping("/recomendacao/{cliente}/tipo")
    private ResponseEntity<CustomerApiResponse> findRecommendationByWine(@PathVariable("cliente") Long id) {
        CustomerApiResponse recommendationWine = customerService.findRecommendationByWine(id);

        if (recommendationWine == null) {
            return ResponseEntity.status(NO_CONTENT).body(null);
        }
        return ResponseEntity.ok().body(recommendationWine);
    }
}
