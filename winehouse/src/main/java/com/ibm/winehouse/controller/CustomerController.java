package com.ibm.winehouse.controller;

import com.ibm.winehouse.response.CustomerApiResponse;
import com.ibm.winehouse.rest.response.CustomerResponse;
import com.ibm.winehouse.service.CustomerService;
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
    public ResponseEntity<List<CustomerResponse>> findLoyalCustomers() {
        List<CustomerResponse> loyalCustomers = customerService.findLoyalCustomers();

        return ResponseEntity.ok().body(loyalCustomers);
    }

    @GetMapping("/recomendacao/{cliente}/tipo")
    public ResponseEntity<CustomerApiResponse> findRecommendationByWine(@PathVariable("cliente") Long id) {
        CustomerApiResponse recommendationWine = customerService.findRecommendationWine(id);

        if (recommendationWine == null) {
            return ResponseEntity.status(NO_CONTENT).body(null);
        }
        return ResponseEntity.ok().body(recommendationWine);
    }
}
