package com.ecommerce.auth_service.dto;

// Lombok automatically generates getter methods.
import lombok.Getter;

// Lombok automatically generates setter methods.
import lombok.Setter;

// Generates a constructor without arguments.
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
// Generates a constructor with all fields as parameters.
import lombok.AllArgsConstructor;

// Generates getter, setter, and constructor methods automatically.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RegistrationRequest {
    // Receives the username entered during registration.
   // Username must contain at least one non-whitespace character.
    @NotBlank(message = "Username is required")
    private String username;

    // Receives the user's email address.
    // Email must not be blank and must follow a valid email format.
    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email address")
    private String email;

    // Receives the original password from the registration form.
    // AuthService will hash it before storing it in the database.
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    // Receives the user's phone number, if provided.
    private String phoneNumber;
    
}
