package com.example.HotelApp.service.impl;

import com.example.HotelApp.persistance.dao.PaymentRepository;
import com.example.HotelApp.persistance.entities.Payment;
import com.example.HotelApp.service.interfaces.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
    }

    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment updatePayment(Long id, Payment payment) {
        Payment existingPayment = getPaymentById(id);
        existingPayment.setAmount(payment.getAmount());
        existingPayment.setMethod(payment.getMethod());
        existingPayment.setReservation(payment.getReservation());
        return paymentRepository.save(existingPayment);
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
    
}
