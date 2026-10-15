
package com.ecommerce.order_service.service;

import com.ecommerce.order_service.entity.Order;
import com.ecommerce.order_service.entity.OrderStatus;
import com.ecommerce.order_service.repository.OrderRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

// Marks this class as the business logic layer.
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    // Spring injects the repository through this constructor.
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Creates a new order and saves it in the database.
    public Order createOrder(Order order) {

        // Every new order starts with PENDING status.
        order.setStatus(OrderStatus.PENDING);

        // Record the order creation time.
        order.setCreatedAt(LocalDateTime.now());

        // Save the order and return the saved entity.
        return orderRepository.save(order);
    }

    // Retrieves all orders belonging to a customer.
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    // Retrieves an order using its unique ID.
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
}
