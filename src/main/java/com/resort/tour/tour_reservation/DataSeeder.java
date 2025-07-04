package com.resort.tour.tour_reservation;

import com.resort.tour.tour_reservation.model.Tour;
import com.resort.tour.tour_reservation.service.TourService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * The DataSeeder class is responsible for seeding the database with initial tour data when the application starts.
 */

@Component
public class DataSeeder implements CommandLineRunner {

    private final TourService tourService;

    public DataSeeder(TourService tourService) {
        this.tourService = tourService;
    }


    /**
     * Runs when the application starts and seeds predefined tours into the database.
     * 
     * @param args Command-line arguments (not used here)
     * @throws Exception If there's an error during seeding
     */

    @Override
    public void run(String... args) throws Exception {
        // Hardcoded Tours
        Tour cityTour = new Tour();
        cityTour.setDate(LocalDate.of(2025, 01, 05));
        cityTour.setDescription("City Tour");
        cityTour.setCost(BigDecimal.valueOf(100.50));
        cityTour.setMaxGuests(20);
        cityTour.setReservedGuests(0);

        Tour beachDay = new Tour();
        beachDay.setDate(LocalDate.of(2025, 02, 15));
        beachDay.setDescription("Beach Day");
        beachDay.setCost(BigDecimal.valueOf(75.00));
        beachDay.setMaxGuests(15);
        beachDay.setReservedGuests(0);

        Tour mountainHike = new Tour();
        mountainHike.setDate(LocalDate.of(2025, 03, 29));
        mountainHike.setDescription("Mountain Hike");
        mountainHike.setCost(BigDecimal.valueOf(120.00));
        mountainHike.setMaxGuests(10);
        mountainHike.setReservedGuests(0);

        // Save Tours to the Database
        tourService.saveAllTours(Arrays.asList(cityTour, beachDay, mountainHike));
    }
}
