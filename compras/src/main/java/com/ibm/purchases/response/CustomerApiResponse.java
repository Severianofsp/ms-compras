package com.ibm.purchases.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerApiResponse {
    private Long id;
    private String name;
    private String recommendation;
}
