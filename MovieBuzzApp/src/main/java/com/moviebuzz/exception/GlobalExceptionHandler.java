package com.moviebuzz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = {"com.moviebuzz.controller","com.moviebuzz.exception"})
public class GlobalExceptionHandler {
	
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }
    
    @ExceptionHandler(NullAttributeException.class)
    public ResponseEntity<String> handleValidationException(NullAttributeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request: Have you given any attribute a Null value?");
    }
}

