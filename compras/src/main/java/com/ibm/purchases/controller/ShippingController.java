package com.ibm.purchases.controller;

import com.ibm.purchases.rest.response.ShippingResponse;
import com.ibm.purchases.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
public class ShippingController {

    @Autowired
    private ShippingService shippingService;

    @GetMapping("/compras")
    private ResponseEntity<List<ShippingResponse>> findAllShipping() {
        List<ShippingResponse> allShipping = shippingService.findAllPurchases();
        return ResponseEntity.ok().body(allShipping);
    }

    @GetMapping("/maior-compra/{year}")
    private ResponseEntity<ShippingResponse> findBiggestBuy(@PathVariable("year") Integer year) {
        ShippingResponse biggestBuy = shippingService.findBiggestBuy(year);

        if (biggestBuy == null) {
            return ResponseEntity.status(NO_CONTENT).body(null);
        }
        return ResponseEntity.ok().body(biggestBuy);
    }
}
