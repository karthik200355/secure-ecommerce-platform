package com.ecommerce.order_service.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.lang.annotation.Inherited;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

@Entity

@Table(name = "customer_orders")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //id of the customer who placed the order
    @Column(nullable = false)
    private Long userId;

    //total price of the order
    @Column(nullable =false,precision =12,scale =2)
    private BigDecimal totalAmount;

    //current order status
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private OrderStatus status;

    //date and time when the order was created.
    @Column(nullable = false)
   private LocalDateTime createdAt;    
}
