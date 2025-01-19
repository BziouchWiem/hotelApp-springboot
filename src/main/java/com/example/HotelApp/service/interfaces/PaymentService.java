package com.example.HotelApp.service.interfaces;

import com.example.HotelApp.persistance.entities.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> getAllPayments();
    Payment getPaymentById(Long id);
    Payment savePayment(Payment payment);
    Payment updatePayment(Long id, Payment payment);
    void deletePayment(Long id);
}
