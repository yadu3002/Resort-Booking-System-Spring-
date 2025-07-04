package com.resort.tour.tour_reservation.repository;

import com.resort.tour.tour_reservation.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    Optional<Guest> findByBookingReference(String bookingReference);

}
