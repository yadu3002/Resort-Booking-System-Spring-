package com.resort.tour.tour_reservation.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class BookingService {

    private final RestTemplate restTemplate;


    public BookingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

    }
    /**
     * Fetches booking details using the provided booking reference.
     *
     * @param bookingReference The reference of the booking to retrieve details for
     * @return Booking object containing the booking details or null if not found
     */
    public Booking getBookingDetails(String bookingReference) {
        String url = "https://pmaier.eu.pythonanywhere.com/rbs/booking/" + bookingReference;
        BookingResponse response = restTemplate.getForObject(url, BookingResponse.class);

        if (response != null) {
            return response.getBooking();
        } else {
            return null;
        }
    }

    // DTO class for the booking response
    public static class BookingResponse {
        private Booking booking;

        public Booking getBooking() {
            return booking;
        }

        public void setBooking(Booking booking) {
            this.booking = booking;
        }
    }

    public static class Booking {
        private String name;
        private String reference;

        // Getters and setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }
    }
}
