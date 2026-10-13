package com.ecommerce.auth_service.controller;

//import the registration request DTO
import com.ecommerce.auth_service.dto.RegistrationRequest;

//import the registration respose DTO 
import com.ecommerce.auth_service.dto.RegisterResponse;

//import the service that contains registration logic
import com.ecommerce.auth_service.service.AuthService;

import jakarta.validation.Valid;

//identify this class as a REST API controller.
import org.springframework.web.bind.annotation.RestController;

//maps HTTP requests to methods in the controller
import org.springframework.web.bind.annotation.RequestMapping;

//Maps POST request to a specific methos.
import org.springframework.web.bind.annotation.PostMapping;

//convert the incoming json request bosy into a java object
import org.springframework.web.bind.annotation.RequestBody;

//Enable validationof incoming request data
import org.springframework.validation.annotation.Validated;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController 
@RequestMapping("/api/auth")
@Validated
public class AuthController {
    // Stores the service dependency used to process registration.
    private final AuthService authService;

     // Constructor injection lets Spring provide the AuthService object.
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Returns HTTP 201 when a new user is successfully registered.
@ResponseStatus(HttpStatus.CREATED)
     // Handles POST requests sent to /api/auth/register.
    @PostMapping("/register")
    public RegisterResponse registerUser(
        // @Valid tells Spring to check the validation rules in RegisterRequest.
@Valid @RequestBody RegistrationRequest request
            ) {

        // Passes the registration request to the service and returns its response.
        return authService.registerUser(request);
    }
}
