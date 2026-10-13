package com.ecommerce.auth_service.service;

// Imports Spring's annotation for registering this class as a service.
import org.springframework.stereotype.Service;

import com.ecommerce.auth_service.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;




import com.ecommerce.auth_service.dto.RegistrationRequest;
import com.ecommerce.auth_service.dto.RegisterResponse;
import com.ecommerce.auth_service.entity.User;


// to access the repository we are connecting to repository which is database
@Service 
public class AuthService {
    private final UserRepository UserRepository; //dependeny injection
    private final PasswordEncoder passwordEncoder; // password encoder to hash password securely

    public AuthService(UserRepository UserRepository ,PasswordEncoder passwordEncoder){ //constructor dependency injection
        this.UserRepository = UserRepository;

        this.passwordEncoder  = passwordEncoder;
    }

    
    // Checks whether an account already exists for the supplied email.
    public boolean isEmailAlreadyRegistered(String email) {

        // Ask the repository to check the database for this email.
        return UserRepository.existsByEmail(email);
    }
    
    // Converts a plain-text password into a secure BCrypt hash.
    public String hashPassword(String rawPassword) {

        // Use the PasswordEncoder bean to hash the password.
        return passwordEncoder.encode(rawPassword);
    }

     // Registers a new user using the details received from the frontend.
    public RegisterResponse registerUser(RegistrationRequest request){

         // Stop registration if another account already uses this email.
         if(UserRepository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("Email is already registered");
         }

          // Create an entity that represents the new database record.
          User user = new User();

          // Copy the username from the registration request into the entity.
        user.setUsername(request.getUsername());

        // Copy the email into the entity.
        user.setEmail(request.getEmail());

        // Hash the password before storing it; never save the plain password.
        user.setPassword(hashPassword(request.getPassword()));

        // Copy the optional phone number into the entity.
        user.setPhoneNumber(request.getPhoneNumber());

        // Save the user in MySQL and retrieve the saved entity, including its ID.
        User savedUser = UserRepository.save(user);

        // Return only the registration result, user ID, and email.
        return new RegisterResponse(
                "Registration successful",
                savedUser.getId(),
                savedUser.getEmail());
    }
} 
