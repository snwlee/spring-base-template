package com.snwlee.springbasetemplate.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("com.pgrrr.springbasetemplte")
public class GlobalExceptionHandler {

    @ExceptionHandler(ItemException.class)
    public ResponseEntity<ErrorCode> handleBaseException(ItemException exception) {
        return ResponseEntity.status(exception.getHttpStatus()).body(exception.getErrorCode());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleBaseException(Exception exception) {
        return ResponseEntity.internalServerError().body(exception.getMessage());
    }

}
