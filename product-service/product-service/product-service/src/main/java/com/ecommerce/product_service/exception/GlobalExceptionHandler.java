
package com.ecommerce.product_service.exception; // Defines the package containing exception-handling classes.

import org.springframework.http.HttpStatus; // Provides HTTP status codes such as 404 Not Found.
import org.springframework.http.ResponseEntity; // Allows us to return an HTTP status and response body.
import org.springframework.web.bind.annotation.ExceptionHandler; // Connects a specific exception to its handler method.
import org.springframework.web.bind.annotation.RestControllerAdvice; // Applies exception handling across REST controllers.

// Handles exceptions thrown by Product Service REST controllers.
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handles our custom exception when a requested product does not exist.
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(
            ProductNotFoundException exception) {

        // Returns HTTP 404 with a message explaining that the product was not found.
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}

