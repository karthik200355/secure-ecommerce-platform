package com.ecommerce.order_service.repository;

import com.ecommerce.order_service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Provides database operations for Order entities.
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Finds all orders placed by a specific customer.
    List<Order> findByUserId(Long userId);
}