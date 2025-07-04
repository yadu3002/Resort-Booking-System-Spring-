package com.resort.tour.tour_reservation.service;

import com.resort.tour.tour_reservation.model.Guest;
import com.resort.tour.tour_reservation.model.Reservation;
import com.resort.tour.tour_reservation.model.Tour;
import com.resort.tour.tour_reservation.repository.GuestRepository;
import com.resort.tour.tour_reservation.repository.ReservationRepository;
import com.resort.tour.tour_reservation.repository.TourRepository;

import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final TourRepository tourRepository;
    private final BookingService bookingService;
    private final GuestRepository guestRepository; 

    public ReservationService(ReservationRepository reservationRepository, GuestRepository guestRepository, BookingService bookingService, TourRepository tourRepository) {
        this.reservationRepository = reservationRepository;
        this.bookingService = bookingService;
        this.tourRepository = tourRepository;
        this.guestRepository = guestRepository;


    }
    /**
     * Retrieves all reservations for a given guest by their ID.
     *
     * @param guestId ID of the guest
     * @return List of reservations for the guest
     */

   public List<Reservation> getReservationsByGuest(Long guestId) {
        Guest guest = guestRepository.findById(guestId)
        .orElseThrow(() -> new RuntimeException("Guest not found"));
return guest.getReservations();
        
    }
/**
     * Retrieves all reservations for a given tour by its ID.
     *
     * @param tourId ID of the tour
     * @return List of reservations for the tour
     */

    public List<Reservation> getReservationsByTour(Long tourId) {
        return reservationRepository.findByTourId(tourId);
    }

    /**
     * Creates a reservation for a guest on a tour.
     * If the guest is not found, a new guest will be created.
     * Checks if there are available spots on the tour before creating the reservation.
     *
     * @param tourId ID of the tour to reserve
     * @param bookingReference Unique booking reference
     * @param guestId ID of the guest making the reservation
     * @return The created reservation
     */
    public Reservation createReservation(Long tourId, String bookingReference,Long guestId) {
        // Fetch booking details using the bookingReference
        BookingService.Booking booking = bookingService.getBookingDetails(bookingReference);

        

        if (booking != null) {
            Guest guest = guestRepository.findByBookingReference(bookingReference)
            .orElseGet(() -> {
                // Create and save new guest if not found
                Guest newGuest = new Guest();
                newGuest.setName(booking.getName());
                newGuest.setBookingReference(bookingReference);
                return guestRepository.save(newGuest);
            });
        Tour tour = tourRepository.findById(tourId).orElseThrow(() -> new RuntimeException("Tour not found"));

            // Check if there are available spots on the tour
            if (tour.getReservedGuests() < tour.getMaxGuests()) {
                // Create the reservation


                Reservation reservation = new Reservation();
                reservation.setTourId(tourId);
                reservation.setBookingReference(booking.getReference());
                reservation.setGuest(guest); // Associate the Guest with the Reservation
                reservation.setGuestName(booking.getName());

                // Increment the reserved count for the tour
                tour.incrementReservedGuest();
                tourRepository.save(tour);  // Save the updated tour with the incremented reserved count

                // Save the new reservation
                return reservationRepository.save(reservation);
            } else {
                throw new RuntimeException("No available spots for this tour");
            }
        } else {
            throw new RuntimeException("Booking details not found for reference: " + bookingReference);
        }
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}