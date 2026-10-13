package com.ecommerce.auth_service.dto;

// Generates getter methods for the response fields.
import lombok.Getter;

// Generates setter methods for the response fields.
import lombok.Setter;

// Generates a constructor without arguments.
import lombok.NoArgsConstructor;


// Generates a constructor containing all fields.
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RegisterResponse {
     // Message describing the registration result.
    private String message;

    // ID assigned to the newly registered user.
    private Long userId;

    // Email address associated with the registered account.
    private String email;
    
}
