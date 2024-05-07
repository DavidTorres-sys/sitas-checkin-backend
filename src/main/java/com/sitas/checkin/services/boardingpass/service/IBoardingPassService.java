package com.sitas.checkin.services.boardingpass.service;

import com.sitas.checkin.domain.model.user.BoardingPass;
import org.springframework.http.ResponseEntity;

/**
 * Service interface for managing boarding passes.
 */
public interface IBoardingPassService {

    /**
     * Creates a new boarding pass.
     *
     * @param lastName     The last name of the passenger.
     * @param flightNumber The flight number.
     */
    public ResponseEntity<BoardingPass> createBoardingPass(String lastName, String flightNumber);

    /**
     * Retrieves an existing boarding pass.
     *
     * @param boardingPassId The boarding pass to retrieve.
     * @return A ResponseEntity containing either:
     *         - HTTP status 200 (OK) and the boarding pass associated with the provided ID.
     *         - HTTP status 404 (Not Found) if no boarding pass is found for the provided ID.
     */
    public ResponseEntity<BoardingPass> getBoardingPass(Integer boardingPassId);

    /**
     * Retrieves the boarding pass associated with a specific passenger ID.
     *
     * @param passengerId The unique identifier of the passenger.
     * @return A ResponseEntity containing either:
     *         - HTTP status 200 (OK) and the boarding pass associated with the provided passenger ID.
     *         - HTTP status 404 (Not Found) if no boarding pass is found for the provided passenger ID.
     */
    public ResponseEntity<BoardingPass> getBoardingPassByPassenger(Integer passengerId);

    /**
     * Deletes a boarding pass by its ID.
     *
     * @param boardingPassId The ID of the boarding pass to delete.
     * @return ResponseEntity indicating the outcome of the deletion operation.
     *         If the boarding pass is deleted successfully, returns ResponseEntity with status 200 (OK)
     *         and a success message. If the boarding pass is not found, returns ResponseEntity with
     *         status 404 (Not Found). If an unexpected error occurs, returns ResponseEntity with
     *         status 500 (Internal Server Error) and an error message.
     */
    public ResponseEntity<String> deleteBoardingPass(Integer boardingPassId);
}
