package com.ibm.winehouse.rest;

import com.ibm.winehouse.rest.response.CustomerResponse;
import com.ibm.winehouse.rest.response.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;
import java.util.List;

@Validated
@FeignClient(name = "mock", url = "http://www.mocky.io")
public interface MockClient {

    @GetMapping("/v2/598b16291100004705515ec5")
    List<CustomerResponse> getClientList();

    @Valid
    @GetMapping("/v2/598b16861100004905515ec7")
    List<OrderResponse> getOrderList();
}
