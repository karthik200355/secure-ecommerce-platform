package com.ecommerce.payment_service.service;

import com.ecommerce.payment_service.entity.Payment;
import com.ecommerce.payment_service.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    // Constructor injection: provides database access through the repository
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    // Save a new payment in the database
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // Retrieve all payment records
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Retrieve one payment using its ID; return null if it doesn't exist
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    // Update the status of an existing payment using its ID
public Payment updatePaymentStatus(Long id, String status) {

    // Find the payment; throw an error if it doesn't exist
    Payment payment = paymentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Payment not found"));

    // Update the payment status
    payment.setStatus(status);

    // Save the updated payment in MySQL
    return paymentRepository.save(payment);
}
}