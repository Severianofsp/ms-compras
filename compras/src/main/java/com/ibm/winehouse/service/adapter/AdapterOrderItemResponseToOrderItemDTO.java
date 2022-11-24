package com.ibm.winehouse.service.adapter;

import com.ibm.winehouse.dto.OrderItemDTO;
import com.ibm.winehouse.rest.response.OrderItemResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdapterOrderItemResponseToOrderItemDTO {

    public List<OrderItemDTO> adapter(List<OrderItemResponse> items) {
        List<OrderItemDTO> orderItemDTOList = new ArrayList<>();

        for (OrderItemResponse orderItemResponse : items) {
            OrderItemDTO orderItemDTO = OrderItemDTO
                    .builder()
                    .code(orderItemResponse.getCode())
                    .category(orderItemResponse.getCategory())
                    .country(orderItemResponse.getCountry())
                    .harvest(orderItemResponse.getHarvest())
                    .price(orderItemResponse.getPrice())
                    .product(orderItemResponse.getProduct())
                    .variety(orderItemResponse.getVariety())
                    .build();

            orderItemDTOList.add(orderItemDTO);
        }

        return orderItemDTOList;
    }
}
