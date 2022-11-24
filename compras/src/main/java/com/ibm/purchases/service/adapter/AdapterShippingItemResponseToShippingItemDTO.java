package com.ibm.purchases.service.adapter;

import com.ibm.purchases.dto.ShippingItemDTO;
import com.ibm.purchases.rest.response.ShippingItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdapterShippingItemResponseToShippingItemDTO {

    public List<ShippingItemDTO> adapter(List<ShippingItem> items) {
        List<ShippingItemDTO> shippingItemDTOList = new ArrayList<>();

        for (ShippingItem shippingItem : items) {
            ShippingItemDTO shippingItemDTO = ShippingItemDTO
                    .builder()
                    .code(shippingItem.getCode())
                    .category(shippingItem.getCategory())
                    .country(shippingItem.getCountry())
                    .harvest(shippingItem.getHarvest())
                    .price(shippingItem.getPrice())
                    .product(shippingItem.getProduct())
                    .variety(shippingItem.getVariety())
                    .build();

            shippingItemDTOList.add(shippingItemDTO);
        }

        return shippingItemDTOList;
    }
}
