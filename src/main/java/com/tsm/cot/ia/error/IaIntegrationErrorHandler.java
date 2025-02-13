package com.tsm.cot.ia.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class IaIntegrationErrorHandler {


    @ExceptionHandler(IaIntegrationError.class)
    public ResponseEntity<IaIntegrationError> iaErrorHandler(IaIntegrationError e){
        return ResponseEntity.internalServerError().body(e);
    }
}
