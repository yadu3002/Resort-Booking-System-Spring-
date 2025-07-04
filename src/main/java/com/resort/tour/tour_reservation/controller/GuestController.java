package com.resort.tour.tour_reservation.controller;

import com.resort.tour.tour_reservation.model.Reservation;
import com.resort.tour.tour_reservation.model.Tour;
import com.resort.tour.tour_reservation.service.ReservationService;
import com.resort.tour.tour_reservation.service.TourService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guest")
public class GuestController {
    private final TourService tourService;
    private final ReservationService reservationService;

    public GuestController(TourService tourService, ReservationService reservationService) {
        this.tourService = tourService;
        this.reservationService = reservationService;
    }

    /**
     * Fetches all available tours.
     *
     * @return List of all tours
     */
    @GetMapping("/tours")
    public List<Tour> getAllTours() {
        return tourService.getAllTours();
    }
     /**
     * Retrieves reservations for a specific guest.
     *
     * @param guestId ID of the guest
     * @return List of reservations associated with the guest
     */
    @GetMapping("{guestId}")
    public List<Reservation> getReservationsByGuest(@PathVariable Long guestId) {
        return reservationService.getReservationsByGuest(guestId);
    }

     /**
     * Creates a reservation for a guest on a specific tour.
     * 
     * @param guestId Guest's ID
     * @param tourId Tour's ID
     * @param bookingReference Unique booking reference
     * @return The created reservation
     * @throws IllegalArgumentException if the guestId is null
     */
     @PostMapping("/reservations")
public Reservation createReservation(@RequestParam(required = true) Long guestId, 
                                     @RequestParam Long tourId, 
                                     @RequestParam String bookingReference) {
    if (guestId == null) {
        throw new IllegalArgumentException("Guest ID is required");
    }
    return reservationService.createReservation(guestId,bookingReference, tourId );
}

    @GetMapping("/reservations/{guestId}")
    public List<Reservation> getReservations(@PathVariable Long guestId) {
        return reservationService.getReservationsByGuest(guestId);
    }

    @DeleteMapping("/reservations/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }
}