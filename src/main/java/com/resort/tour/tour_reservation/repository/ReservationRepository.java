package com.resort.tour.tour_reservation.repository;

import com.resort.tour.tour_reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for performing CRUD operations on the Reservation entity.
 */

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
     /**
     * Find all reservations associated with a specific guest.
     * 
     * @param guestId the ID of the guest
     * @return a list of reservations for the guest
     */
    List<Reservation> findByGuestId(Long guestId);

    /**
     * Find all reservations associated with a specific tour.
     * 
     * @param tourId the ID of the tour
     * @return a list of reservations for the tour
     */
    List<Reservation> findByTourId(Long tourId);
}
