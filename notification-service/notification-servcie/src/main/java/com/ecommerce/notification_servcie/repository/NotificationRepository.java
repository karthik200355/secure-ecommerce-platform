package com.ecommerce.notification_servcie.repository;

// Import the JPA repository interface.
import org.springframework.data.jpa.repository.JpaRepository;

// Import our Notification entity.
import com.ecommerce.notification_servcie.entity.Notification;

// Provides database operations for Notification records.
// JpaRepository gives save(), findAll(), findById(), and deleteById().
public interface NotificationRepository
        extends JpaRepository<Notification, Long> {
}