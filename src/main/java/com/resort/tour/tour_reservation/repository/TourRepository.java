package com.resort.tour.tour_reservation.repository;

import com.resort.tour.tour_reservation.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<Tour, Long> {
}
