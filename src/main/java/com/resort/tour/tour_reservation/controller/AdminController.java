package com.resort.tour.tour_reservation.controller;

import com.resort.tour.tour_reservation.model.Reservation;
import com.resort.tour.tour_reservation.model.Tour;
import com.resort.tour.tour_reservation.service.TourService;
import com.resort.tour.tour_reservation.service.ReservationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final ReservationService reservationService;
    private final TourService tourService;


    public AdminController(ReservationService reservationService,TourService tourService ) {
        this.reservationService = reservationService;
        this.tourService = tourService;

    }
    /**
     * Retrieves all reservations for a specific tour.
     *
     * @param tourId ID of the tour
     * @return List of reservations for the tour
     */

    @GetMapping("/reservations/{tourId}")
    public List<Reservation> getReservationsByTour(@PathVariable Long tourId) {
        return reservationService.getReservationsByTour(tourId);
    }
     /**
     * Creates a new tour.
     *
     * @param tour The tour details to be created
     * @return ResponseEntity with the created tour and status CREATED
     */
    @PostMapping("/tours")
    public ResponseEntity<Tour> createTour(@RequestBody Tour tour) {
        Tour createdTour = tourService.createTour(tour);
        return new ResponseEntity<>(createdTour, HttpStatus.CREATED);
    }

    // Update an existing tour
    @PutMapping("/tours/{id}")
    public ResponseEntity<Tour> updateTour(@PathVariable Long id, @RequestBody Tour tour) {
        Tour updatedTour = tourService.updateTour(id, tour);
        return new ResponseEntity<>(updatedTour, HttpStatus.OK);
    }

    // Delete a tour
    @DeleteMapping("/tours/{id}")
    public ResponseEntity<Void> deleteTour(@PathVariable Long id) {
        tourService.deleteTour(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // List all tours (optional, for admin overview)
    @GetMapping("/tours")
    public List<Tour> getAllTours() {
        return tourService.getAllTours();
    }
}   
