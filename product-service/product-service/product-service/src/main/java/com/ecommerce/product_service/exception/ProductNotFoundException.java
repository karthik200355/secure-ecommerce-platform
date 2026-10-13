
package com.ecommerce.product_service.exception; // Defines the package for custom exceptions.

// Creates a custom exception for products that do not exist.
public class ProductNotFoundException extends RuntimeException {

    // Constructor accepts a message describing which product was not found.
    public ProductNotFoundException(String message) {
        super(message); // Passes the message to the parent RuntimeException class.
    }
}

