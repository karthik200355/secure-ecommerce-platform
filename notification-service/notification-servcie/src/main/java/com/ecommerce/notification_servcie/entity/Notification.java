
package com.ecommerce.notification_servcie.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

// Maps this Java class to the notifications table in MySQL.
@Entity
@Table(name = "notifications")
public class Notification {

    // Primary key: MySQL generates a unique ID automatically.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Recipient email is mandatory and must have a valid email format.
    @NotBlank(message = "Recipient email is required")
    @Email(message = "Please provide a valid email address")
    @Column(nullable = false)
    private String recipientEmail;

    // Notification subject cannot be empty or contain only spaces.
    @NotBlank(message = "Subject is required")
    @Column(nullable = false)
    private String subject;

    // Notification message is mandatory.
    @NotBlank(message = "Message is required")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    // Tracks delivery state: PENDING, SENT, or FAILED.
    @Column(nullable = false)
    private String status = "PENDING";

    // Records when this notification was created.
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // JPA requires a no-argument constructor.
    public Notification() {
    }

    // Returns the notification ID.
    public Long getId() {
        return id;
    }

    // Sets the notification ID.
    public void setId(Long id) {
        this.id = id;
    }

    // Returns the recipient email.
    public String getRecipientEmail() {
        return recipientEmail;
    }

    // Sets the recipient email.
    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    // Returns the notification subject.
    public String getSubject() {
        return subject;
    }

    // Sets the notification subject.
    public void setSubject(String subject) {
        this.subject = subject;
    }

    // Returns the notification message.
    public String getMessage() {
        return message;
    }

    // Sets the notification message.
    public void setMessage(String message) {
        this.message = message;
    }

    // Returns the current delivery status.
    public String getStatus() {
        return status;
    }

    // Updates the delivery status.
    public void setStatus(String status) {
        this.status = status;
    }

    // Returns the creation timestamp.
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Updates the creation timestamp.
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
