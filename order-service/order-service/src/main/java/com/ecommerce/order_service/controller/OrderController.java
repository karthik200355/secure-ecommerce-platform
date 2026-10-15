package com.ecommerce.order_service.controller;

import com.ecommerce.order_service.dto.OrderRequest;
import com.ecommerce.order_service.entity.Order;
import com.ecommerce.order_service.service.OrderService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.math.BigDecimal;

// Exposes REST APIs for order operations.
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    // Spring injects OrderService automatically.
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // POST /api/orders — create an order after validating the request.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@Valid @RequestBody OrderRequest request) {

        // Create an Order entity from the validated DTO.
        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setTotalAmount(request.getTotalAmount());

        // Set a default status; the service will also set it.
        order.setStatus(com.ecommerce.order_service.entity.OrderStatus.PENDING);

        // Save the order through the service layer.
        return orderService.createOrder(order);
    }

    // GET /api/orders/user/1 — retrieve all orders for a customer.
    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    // GET /api/orders/1 — retrieve one order.
    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);

        // Return 404 if the order does not exist.
        if (order == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Order not found"
            );
        }

        return order;
    }
}