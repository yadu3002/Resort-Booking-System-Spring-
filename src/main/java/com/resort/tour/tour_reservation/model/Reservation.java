package com.resort.tour.tour_reservation.model;

import jakarta.persistence.*;

/**
 * Represents a reservation made by a guest for a specific tour.
 */

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String bookingReference;  // Unique booking reference

    @ManyToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "guest_id", nullable = false)
    private Guest guest;  // A reservation is associated with one guest

   

    @Column(nullable = false)
    
    private String guestName;  
    private Long tourId;  

    // Getters and Setters

     /**
     * Get the unique reservation ID.
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookingReference() {
        return bookingReference;
    }

    public void setBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public Long getTourId() {
        return tourId;
    }

    public void setTourId(Long tourId) {
        this.tourId = tourId;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

}
