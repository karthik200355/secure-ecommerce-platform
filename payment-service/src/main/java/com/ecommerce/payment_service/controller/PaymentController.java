
package com.ecommerce.payment_service.controller;

import com.ecommerce.payment_service.entity.Payment;
import com.ecommerce.payment_service.service.PaymentService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    // Service layer handles payment-related business logic.
    private final PaymentService paymentService;

    // Constructor injection provides the PaymentService dependency.
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // GET http://localhost:8087/api/payments/health
    // Checks whether the controller is reachable.
    @GetMapping("/health")
    public String healthCheck() {
        return "Payment Service is running!";
    }

    // POST http://localhost:8087/api/payments
    // Creates a mock payment record.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Payment createPayment(@RequestBody Payment payment) {

        // New payments must start with PENDING status.
        payment.setStatus("PENDING");

        return paymentService.createPayment(payment);
    }

    // GET http://localhost:8087/api/payments
    // Returns all payment records.
    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    // GET http://localhost:8087/api/payments/1
    // Returns one payment by ID.
    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id) {

        Payment payment = paymentService.getPaymentById(id);

        // Return HTTP 404 if the payment does not exist.
        if (payment == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Payment not found"
            );
        }

        return payment;
    }

    // PATCH http://localhost:8087/api/payments/1/status?status=SUCCESS
    // Updates the status of an existing mock payment.
    @PatchMapping("/{id}/status")
    public Payment updatePaymentStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        // Allow only the supported payment statuses.
        if (!status.equals("PENDING")
                && !status.equals("SUCCESS")
                && !status.equals("FAILED")) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid payment status"
            );
        }

        try {
            return paymentService.updatePaymentStatus(id, status);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Payment not found"
            );
        }
    }
}
