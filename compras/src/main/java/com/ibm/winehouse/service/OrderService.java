package com.ibm.winehouse.service;

import com.ibm.winehouse.rest.MockClient;
import com.ibm.winehouse.rest.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private MockClient mockClient;


    public List<OrderResponse> findAllOrders() {
        List<OrderResponse> orderResponseList = mockClient.getOrderList();
        Collections.sort(orderResponseList);

        return orderResponseList.stream()
                .filter(o -> !o.getTotal().equals(BigDecimal.ZERO))
                .collect(Collectors.toList());
    }

    public OrderResponse findBiggestOrder(Integer year) {
        List<OrderResponse> orderResponse = mockClient.getOrderList();

        return orderResponse
                .stream()
                .filter(sr -> sr.getDate().getYear() == year)
                .max(Comparator.comparing(OrderResponse::getTotal))
                .orElse(new OrderResponse());
    }

}
