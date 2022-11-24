package com.ibm.winehouse.service.adapter;

import com.ibm.winehouse.dto.OrderDTO;
import com.ibm.winehouse.rest.response.CustomerResponse;
import com.ibm.winehouse.rest.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.ibm.winehouse.util.DocumentUtil.getOnlyNumbers;

@Service
public class AdapterOrderResponseToOrderDTO {

    @Autowired
    private AdapterOrderItemResponseToOrderItemDTO adapterOrderItemResponseToOrderItemDTO;

    public List<OrderDTO> adapter(CustomerResponse customerResponse, List<OrderResponse> orderResponseList) {
        List<OrderDTO> orderDTOS = new ArrayList<>();

        List<OrderResponse> getAllShippingByDocument = orderResponseList
                .stream()
                .filter(r -> getOnlyNumbers(r.getClient()).equals(getOnlyNumbers(customerResponse.getDocument())))
                .collect(Collectors.toList());

        for (OrderResponse orderResponse : getAllShippingByDocument) {
            OrderDTO orderDTO = OrderDTO.builder()
                    .code(orderResponse.getCode())
                    .date(orderResponse.getDate())
                    .total(orderResponse.getTotal())
                    .items(adapterOrderItemResponseToOrderItemDTO.adapter(orderResponse.getItems()))
                    .build();

            orderDTOS.add(orderDTO);
        }
        return orderDTOS;
    }
}
