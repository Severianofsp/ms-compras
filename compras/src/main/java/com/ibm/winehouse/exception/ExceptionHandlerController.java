package com.ibm.winehouse.exception;


import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Response> badRequest(NumberFormatException ex) {

        Response response = Response.builder().code(400).message(ex.getMessage()).date(LocalDate.now().toString()).build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Response> restException(FeignException ex) {
        Response response = Response.builder().code(ex.status()).message("Error when request mock").date(LocalDate.now().toString()).build();

        return ResponseEntity.status(ex.status()).body(response);
    }
}
