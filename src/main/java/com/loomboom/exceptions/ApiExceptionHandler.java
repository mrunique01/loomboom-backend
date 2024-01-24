package com.loomboom.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.loomboom.dto.exception.ExceptionResponse;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = ApiRequestException.class)
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException apiRequestException) {
        return ResponseEntity.status(apiRequestException.getStatus())
                .body(new ExceptionResponse(false, apiRequestException.getMessage()));
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Object> accountDisabledException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse(false, "Please Enter Valid Id"));
    }

}
