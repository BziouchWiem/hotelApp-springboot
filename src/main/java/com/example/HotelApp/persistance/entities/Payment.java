package com.example.HotelApp.persistance.entities;

import jakarta.persistence.*;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private String method;

    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    private Booking reservation;
    

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Booking getReservation() {
        return reservation;
    }

    public void setReservation(Booking reservation) {
        this.reservation = reservation;
    }
}
