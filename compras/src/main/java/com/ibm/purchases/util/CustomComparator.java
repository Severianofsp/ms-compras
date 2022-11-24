package com.ibm.purchases.util;

import com.ibm.purchases.dto.CustomerDTO;
import com.ibm.purchases.dto.ShippingDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;

public class CustomComparator implements Comparator<CustomerDTO> {

    @Override
    public int compare(CustomerDTO o1, CustomerDTO o2) {
        BigDecimal totalO1 = o1
                .getPurchases()
                .stream()
                .map(ShippingDTO::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(o1.getPurchases().size()),
                        RoundingMode.HALF_UP);

        BigDecimal totalO2 = o2
                .getPurchases()
                .stream()
                .map(ShippingDTO::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(o2.getPurchases().size()),
                        RoundingMode.HALF_UP);

        return totalO2.compareTo(totalO1);
    }
}
