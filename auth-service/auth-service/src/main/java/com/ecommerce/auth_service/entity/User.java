package com.ecommerce.auth_service.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import lombok.Getter;
import lombok.Setter;

import lombok.NoArgsConstructor;

import jakarta.persistence.Table;
import jakarta.persistence.Column;


@Entity
@Getter 
@Setter 
@NoArgsConstructor 
@Table(name = "users")

public class User {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    
    private String username;

    @Column (nullable = false,unique = true)
    private String email;

    @Column (nullable = false)
    private String password;

    private String phoneNumber;

    // Sets the phone number for this user.
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    
}
}
