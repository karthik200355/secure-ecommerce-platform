

package com.ecommerce.product_service.entity;
//package com.ecommerce.product_service.entity;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product{

    @Id
    //unique id for every product;generate automatically
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //product name

    private String name;

    //product description
    private String description;

    //Selling price of the product
    private Double price;

    //default constructor requried by jpa'lombok
    
}