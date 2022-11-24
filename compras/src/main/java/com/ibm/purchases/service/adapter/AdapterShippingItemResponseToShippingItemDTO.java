package com.ibm.purchases.service.adapter;

import com.ibm.purchases.dto.ShippingItemDTO;
import com.ibm.purchases.rest.response.ShippingItemResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdapterShippingItemResponseToShippingItemDTO {

    public List<ShippingItemDTO> adapter(List<ShippingItemResponse> items) {
        List<ShippingItemDTO> shippingItemDTOList = new ArrayList<>();

        for (ShippingItemResponse shippingItemResponse : items) {
            ShippingItemDTO shippingItemDTO = ShippingItemDTO
                    .builder()
                    .code(shippingItemResponse.getCode())
                    .category(shippingItemResponse.getCategory())
                    .country(shippingItemResponse.getCountry())
                    .harvest(shippingItemResponse.getHarvest())
                    .price(shippingItemResponse.getPrice())
                    .product(shippingItemResponse.getProduct())
                    .variety(shippingItemResponse.getVariety())
                    .build();

            shippingItemDTOList.add(shippingItemDTO);
        }

        return shippingItemDTOList;
    }
}
