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
     * @param userId The boarding pass to retrieve.
     * @return The retrieved boarding pass.
     */
    public ResponseEntity<BoardingPass> getBoardingPass(int userId);
}
