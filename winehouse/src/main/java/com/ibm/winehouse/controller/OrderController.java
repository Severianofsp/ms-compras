package com.ibm.winehouse.controller;

import com.ibm.winehouse.rest.response.OrderResponse;
import com.ibm.winehouse.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/compras")
    public ResponseEntity<List<OrderResponse>> findAllShipping() {
        List<OrderResponse> allOrders = orderService.findAllOrders();
        return ResponseEntity.ok().body(allOrders);
    }

    @GetMapping("/maior-compra/{year}")
    public ResponseEntity<OrderResponse> findBiggestOrder(@PathVariable("year") Integer year) {
        OrderResponse biggestBuy = orderService.findBiggestOrder(year);

        if (biggestBuy == null) {
            return ResponseEntity.status(NO_CONTENT).body(null);
        }
        return ResponseEntity.ok().body(biggestBuy);
    }
}
