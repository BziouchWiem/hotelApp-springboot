package com.example.HotelApp.controller;

import com.example.HotelApp.persistance.entities.Payment;
import com.example.HotelApp.service.impl.PaymentServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentServiceImpl paymentService;

    public PaymentController(PaymentServiceImpl paymentService) {
        this.paymentService = paymentService;
    }
    
    @GetMapping("/payments")
    public String getAllPayments(Model model) {
        List<Payment> payments = paymentService.getAllPayments();
        model.addAttribute("payments", payments);
        return "payments";
    }

    // Afficher le formulaire de paiement (pour ajouter ou éditer)
    @GetMapping("/add")
    public String addPaymentForm(Model model) {
        model.addAttribute("payment", new Payment());
        return "payment_form"; // Vue pour le formulaire de paiement
    }

    // Sauvegarder le paiement
    @PostMapping("/add")
    public String savePayment(@ModelAttribute Payment payment) {
        paymentService.savePayment(payment);
        return "redirect:/payments"; // Rediriger vers la liste des paiements
    }

    // Autres méthodes comme la liste des paiements, modification, suppression peuvent être ajoutées ici
}
