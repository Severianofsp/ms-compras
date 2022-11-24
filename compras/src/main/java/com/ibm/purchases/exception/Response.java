package com.ibm.purchases.exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response {

    private Integer code;
    private String date;
    private String message;
}
