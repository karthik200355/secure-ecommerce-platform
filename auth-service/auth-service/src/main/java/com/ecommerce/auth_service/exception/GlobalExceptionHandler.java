package com.ecommerce.auth_service.exception;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

// Handles validation errors raised by @Valid.
import org.springframework.web.bind.MethodArgumentNotValidException;

// Collects validation messages into a list.
import java.util.List;

//Handles illegalArgumentexception from our registeration service.
@RestControllerAdvice

public class GlobalExceptionHandler {
     // Handles IllegalArgumentException from our registration service.
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(
            IllegalArgumentException exception) {

        // Creates a JSON response containing the error message.
        Map<String, String> error = Map.of(
                "message", exception.getMessage()
        );

        // Returns HTTP 409 Conflict when registration conflicts with existing data.
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }

    // Handles validation errors from @Valid in the registration request.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
            MethodArgumentNotValidException exception) {

        // Gets the first validation error message from the request.
        String message = exception.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();

        // Creates a JSON response with the validation message.
        Map<String, String> error = Map.of("message", message);

        // Returns HTTP 400 because the request contains invalid data.
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }
    
}
