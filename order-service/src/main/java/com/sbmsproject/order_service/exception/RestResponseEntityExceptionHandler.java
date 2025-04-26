package com.sbmsproject.order_service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomExcpetion.class)
    public ResponseEntity<com.sbmsproject.order_service.external.client.response.ErrorResponse> handleOrderServiceException(CustomExcpetion exception) {
        return new ResponseEntity<>(new com.sbmsproject.order_service.external.client.response.ErrorResponse(exception.getMessage(),
                exception.getErrorCode()),
                HttpStatus.NOT_FOUND);
    }

}
