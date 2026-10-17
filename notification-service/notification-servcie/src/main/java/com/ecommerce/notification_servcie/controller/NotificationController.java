
package com.ecommerce.notification_servcie.controller;

import com.ecommerce.notification_servcie.entity.Notification;
import com.ecommerce.notification_servcie.service.NotificationService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Marks this class as a REST API controller.
@RestController

// Common URL path for all notification APIs.
@RequestMapping("/api/notifications")
public class NotificationController {

    // Service layer handles notification business logic.
    private final NotificationService notificationService;

    // Constructor injection: Spring provides NotificationService automatically.
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // GET /api/notifications/health
    // Checks whether the Notification Service is running.
    @GetMapping("/health")
    public String healthCheck() {
        return "Notification Service is running!";
    }

    // POST /api/notifications
    // Validates and saves a new notification.
    @PostMapping
    public ResponseEntity<Notification> createNotification(
            @Valid @RequestBody Notification notification) {

        // @Valid checks email format and required fields before saving.
        Notification savedNotification =
                notificationService.createNotification(notification);

        // HTTP 201 means the notification was created successfully.
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedNotification);
    }

    // GET /api/notifications
    // Retrieves all notifications from the database.
    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        return ResponseEntity.ok(
                notificationService.getAllNotifications());
    }

    // GET /api/notifications/{id}
    // Retrieves a notification using its ID.
    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotificationById(
            @PathVariable Long id) {

        Notification notification =
                notificationService.getNotificationById(id);

        // Return 404 when the requested ID does not exist.
        if (notification == null) {
            return ResponseEntity.notFound().build();
        }

        // Return HTTP 200 with the notification details.
        return ResponseEntity.ok(notification);
    }
}
