package com.ibm.winehouse.util;

import com.ibm.winehouse.dto.CustomerDTO;
import com.ibm.winehouse.dto.OrderDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;

public class CustomComparator implements Comparator<CustomerDTO> {

    @Override
    public int compare(CustomerDTO o1, CustomerDTO o2) {
        BigDecimal totalO1 = o1
                .getOrders()
                .stream()
                .map(OrderDTO::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(o1.getOrders().size()),
                        RoundingMode.HALF_UP);

        BigDecimal totalO2 = o2
                .getOrders()
                .stream()
                .map(OrderDTO::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(o2.getOrders().size()),
                        RoundingMode.HALF_UP);

        return totalO2.compareTo(totalO1);
    }
}
