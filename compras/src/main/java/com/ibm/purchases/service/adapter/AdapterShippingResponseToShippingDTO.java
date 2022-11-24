package com.ibm.purchases.service.adapter;

import com.ibm.purchases.dto.ShippingDTO;
import com.ibm.purchases.rest.response.CustomerResponse;
import com.ibm.purchases.rest.response.ShippingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.ibm.purchases.util.DocumentUtil.getOnlyNumbers;

@Service
public class AdapterShippingResponseToShippingDTO {

    @Autowired
    private AdapterShippingItemResponseToShippingItemDTO adapterShippingItemResponseToShippingItemDTO;

    public List<ShippingDTO> adapter(CustomerResponse customerResponse, List<ShippingResponse> shippingResponseList) {
        List<ShippingDTO> shippingDTOS = new ArrayList<>();

        List<ShippingResponse> getAllShippingByDocument = shippingResponseList
                .stream()
                .filter(r -> getOnlyNumbers(r.getClient()).equals(getOnlyNumbers(customerResponse.getCpf())))
                .collect(Collectors.toList());

        for (ShippingResponse shippingResponse : getAllShippingByDocument) {
            ShippingDTO shippingDTO = ShippingDTO.builder()
                    .code(shippingResponse.getCode())
                    .date(shippingResponse.getDate())
                    .total(shippingResponse.getTotal())
                    .items(adapterShippingItemResponseToShippingItemDTO.adapter(shippingResponse.getItems()))
                    .build();

            shippingDTOS.add(shippingDTO);
        }
        return shippingDTOS;
    }
}
