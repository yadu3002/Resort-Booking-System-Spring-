package com.resort.tour.tour_reservation.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents a tour in the reservation system, containing key details like date, description, cost, and capacity.
 */
@Entity
public class Tour {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the tour

    private LocalDate date; // Date of the tour

    private String description; // Description of the tour

    private BigDecimal cost; // Cost per guest for the tour

    private int maxGuests; // Maximum number of guests for the tour

    @Column(name = "reserved_guests")
    private int reservedGuests; // Current number of reserved guests

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(int maxGuests) {
        this.maxGuests = maxGuests;
    }

    public int getReservedGuests() {
        return reservedGuests;
    }

    public void setReservedGuests(int reservedGuests) {
        this.reservedGuests = reservedGuests;
    }

    /**
     * Increments the reserved guest count, ensuring it doesn't exceed maxGuests.
     */

    public void incrementReservedGuest() {
        if (reservedGuests < maxGuests) {
            reservedGuests++;
        }

}
}
