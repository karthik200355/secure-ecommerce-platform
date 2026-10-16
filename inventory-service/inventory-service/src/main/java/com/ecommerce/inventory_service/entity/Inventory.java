package com.ecommerce.inventory_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name="inventory")

public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //prodduct id revuved from product servcie
    @Column(nullable=false,unique=true)
    private Long productId;


    //quantity currently availabale for purchase.
    @Column(nullable =false)
    private Integer availableQuantity;

    //quantity temporarly helad for orders
    @Column(nullable =false)
    private Integer reservedQuantity;

    // Time when this inventory record was last updated.
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    
}
