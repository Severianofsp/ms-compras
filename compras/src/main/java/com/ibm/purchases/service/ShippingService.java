package com.ibm.purchases.service;

import com.ibm.purchases.rest.MockClient;
import com.ibm.purchases.rest.response.ShippingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ShippingService {

    @Autowired
    private MockClient mockClient;


    public List<ShippingResponse> findAllPurchases() {
        List<ShippingResponse> shippingResponses = mockClient.getShippingList();
        Collections.sort(shippingResponses);
        return shippingResponses;
    }

    public ShippingResponse findBiggestBuy(Integer year) {
        List<ShippingResponse> shippingResponses = mockClient.getShippingList();

        return shippingResponses
                .stream()
                .filter(sr -> sr.getDate().getYear() == year)
                .max(Comparator.comparing(ShippingResponse::getTotal))
                .orElse(new ShippingResponse());
    }

}
