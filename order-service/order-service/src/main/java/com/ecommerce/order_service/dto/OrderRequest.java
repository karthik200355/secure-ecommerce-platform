package com.ecommerce.order_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;


//dto reciver the customer order request
public class OrderRequest {

    @NotNull(message = "user id is required")
    @Positive(message="user id must be greater than zero")

    private Long userId;

    @NotNull(message="Total amount is required")
    @Positive(message="Total amount must be greater than zero")
    private BigDecimal totalAmount;

    //constructor
    public OrderRequest(){

    }
     // Getter returns the customer ID.
    public Long getUserId() {
        return userId;
    }

    // Setter stores the customer ID received in the request.
    public void setUserId(Long userId) {
        this.userId = userId;
    }

     // Getter returns the order total.
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    // Setter stores the order total received in the request.
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
}
