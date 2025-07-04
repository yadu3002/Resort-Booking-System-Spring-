    package com.resort.tour.tour_reservation.service;

    import com.resort.tour.tour_reservation.model.Tour;
    import com.resort.tour.tour_reservation.repository.TourRepository;
    import org.springframework.stereotype.Service;

    import java.util.List;

    /**
 * Service class for managing Tour operations.
 * Provides methods for creating, updating, deleting, and retrieving tours.
 */

    @Service
    public class TourService {

        private final TourRepository tourRepository;

        public TourService(TourRepository tourRepository) {
            this.tourRepository = tourRepository;
        }

 /**
     * Create a new tour.
     *
     * @param tour the tour entity to be created
     * @return the saved tour
     */
        public Tour createTour(Tour tour) {
            return tourRepository.save(tour);
        }

         /**
     * Update an existing tour.
     *
     * @param id   the ID of the tour to update
     * @param tour the new tour details
     * @return the updated tour
     * @throws IllegalArgumentException if the tour with the given ID doesn't exist
     */
        public Tour updateTour(Long id, Tour tour) {
            Tour existingTour = tourRepository.findById(id).orElseThrow(() ->
                    new IllegalArgumentException("Tour not found with ID: " + id));
            existingTour.setDate(tour.getDate());
            existingTour.setDescription(tour.getDescription());
            existingTour.setCost(tour.getCost());
            existingTour.setMaxGuests(tour.getMaxGuests());
            return tourRepository.save(existingTour);
        }

         /**
     * Delete a tour by ID.
     *
     * @param id the ID of the tour to delete
     * @throws IllegalArgumentException if the tour with the given ID doesn't exist
     */
        public void deleteTour(Long id) {
            if (!tourRepository.existsById(id)) {
                throw new IllegalArgumentException("Tour not found with ID: " + id);
            }
            tourRepository.deleteById(id);
        }

        
    /**
     * Get all available tours.
     *
     * @return a list of all tours
     */
        public List<Tour> getAllTours() {
            return tourRepository.findAll();
        }

        /**
     * Save a list of tours.
     *
     * @param tours a list of tours to save
     */
        public void saveAllTours(List<Tour> tours) { tourRepository.saveAll(tours); }
    }
