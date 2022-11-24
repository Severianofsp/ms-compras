package com.ibm.purchases.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponseException extends RuntimeException {

    private final String response;

    private final HttpStatus status;

    public ErrorResponseException() {
        this(null, null);
    }

    public ErrorResponseException(final String response, final HttpStatus httpStatus) {
        super();
        this.response = response;
        this.status = httpStatus;
    }
}
