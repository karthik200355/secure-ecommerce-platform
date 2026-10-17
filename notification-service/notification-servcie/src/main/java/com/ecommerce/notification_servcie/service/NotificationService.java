package com.ecommerce.notification_servcie.service;

import com.ecommerce.notification_servcie.entity.Notification;
import com.ecommerce.notification_servcie.repository.NotificationRepository;

import org.springframework.stereotype.Service;
import java.util.List;

// Marks this class as the business logic layer.
@Service
public class NotificationService {

    // Repository helps save and retrieve notifications from MySQL.
    private final NotificationRepository notificationRepository;

    // Constructor injection: Spring automatically provides the repository.
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    // Save a new notification in the database.
    public Notification createNotification(Notification notification) {

        // New notifications start with PENDING delivery status.
        notification.setStatus("PENDING");

        // Return the saved record, including its generated ID.
        return notificationRepository.save(notification);
    }

    // Retrieve all notifications stored in the database.
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    // Retrieve one notification using its unique ID.
    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id).orElse(null);
    }
}