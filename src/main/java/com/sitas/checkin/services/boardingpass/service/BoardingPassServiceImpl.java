package com.sitas.checkin.services.boardingpass.service;


import com.sitas.checkin.domain.model.airline.Flight;
import com.sitas.checkin.domain.model.user.*;
import com.sitas.checkin.domain.repository.airline.IFlightRepository;
import com.sitas.checkin.domain.repository.user.IBoardingPassRepository;
import com.sitas.checkin.domain.repository.user.IPassengerRepository;
import com.sitas.checkin.domain.repository.user.IPersonRepository;
import com.sitas.checkin.utils.exception.BusinessException;
import com.sitas.checkin.utils.exception.DataDuplicatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.Optional;

/**
 * Service class for managing boarding passes.
 */
@Service
public class BoardingPassServiceImpl implements IBoardingPassService{

    private final IBoardingPassRepository boardingPassRepository;
    private final IPassengerRepository passengerRepository;
    private final IFlightRepository flightRepository;
    private final IPersonRepository personRepository;

    @Autowired
    public BoardingPassServiceImpl(IBoardingPassRepository boardingPassRepository,
                                   IPassengerRepository passengerRepository,
                                   IPersonRepository personRepository,
                                   IFlightRepository flightRepository) {
        this.boardingPassRepository = boardingPassRepository;
        this.passengerRepository = passengerRepository;
        this.flightRepository = flightRepository;
        this.personRepository = personRepository;
    }

    /**
     * Creates a new boarding pass.
     *
     * @param lastName     The last name of the passenger.
     * @param flightNumber The flight number.
     * @return ResponseEntity with HTTP status 201 (CREATED) and the created boarding pass if successful,
     *         ResponseEntity with HTTP status 409 (CONFLICT) if the boarding pass already exists,
     *         ResponseEntity with HTTP status 500 (INTERNAL_SERVER_ERROR) if an unexpected error occurs.
     * @throws DataDuplicatedException    If the boarding pass already exists in the database.
     * @throws BusinessException          If a data integrity violation or database error occurs.
     * @throws IllegalArgumentException  If an invalid argument is passed.
     * @throws ResponseStatusException    If an unexpected error occurs during the operation.
     *
     */
    @Override
    public ResponseEntity<BoardingPass> createBoardingPass(String lastName, String flightNumber) {
        try {
            // Find passenger by last name
            Person person = personRepository.findByLastName(lastName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Passenger not found"));

            // Find flight by flight number
            Flight flight = flightRepository.findByFlightNumber(flightNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Flight not found"));

            // Find passenger by id
            Passenger passenger = passengerRepository.findById(person.getPersonId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Passenger not found"));

            // Create a new boarding pass
            BoardingPass boardingPass = new BoardingPass();
            boardingPass.setPassenger(passenger);
            boardingPass.setFlight(flight);
            // Assuming you have default values for medicalInfo and luggageInfo
            boardingPass.setMedicalInfo(new MedicalInfo());
            boardingPass.setLuggageInfo(new LuggageInfo());
            boardingPass.setBoardingTime(new Timestamp(System.currentTimeMillis()));

            // Save the boarding pass
            BoardingPass createdBoardingPass = boardingPassRepository.save(boardingPass);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdBoardingPass);

        } catch (DataIntegrityViolationException e) {
            // Handle data integrity violations
            throw new BusinessException("Data integrity violation");
        } catch (DataAccessException e) {
            // Handle database access errors
            throw new BusinessException("Database error");
        } catch (IllegalArgumentException e) {
            // Handle illegal argument exceptions
            throw new IllegalArgumentException("Invalid argument: " + e.getMessage(), e);
        } catch (Throwable e) {
            // Handle other unexpected errors
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create boarding pass", e);
        }
    }

    /**
     * Retrieves an existing boarding pass for the given passenger ID, only read method.
     *
     * @param passengerId The ID of the passenger to retrieve the boarding pass for.
     * @return ResponseEntity containing the retrieved boarding pass, or 404 Not Found if not found.
     * @throws BusinessException          If a data integrity violation or database error occurs.
     * @throws IllegalArgumentException  If an invalid argument is passed.
     * @throws ResponseStatusException    If an unexpected error occurs during the operation.
     */
    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<BoardingPass> getBoardingPass(int passengerId) {
        try {
            // Retrieve the boarding pass associated with the passenger ID
            Optional<BoardingPass> optionalBoardingPass = boardingPassRepository.findById(passengerId);
            // Check if the boarding pass exists
            if (optionalBoardingPass.isPresent()) {
                // Boarding pass found, return it
                BoardingPass boardingPass = optionalBoardingPass.get();
                return ResponseEntity.ok(boardingPass);
            } else {
                // Boarding pass not found, return a 404 Not Found response
                return ResponseEntity.notFound().build();
            }
        } catch (DataIntegrityViolationException e) {
            // Handle data integrity violations
            throw new BusinessException("Data integrity violation");
        } catch (DataAccessException e) {
            // Handle database access errors
            throw new BusinessException("Database error");
        } catch (IllegalArgumentException e) {
            // Handle illegal argument exceptions
            throw new IllegalArgumentException("Invalid argument: " + e.getMessage(), e);
        }catch (Throwable e) {
            // Handle unexpected errors
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve boarding pass", e);
        }
    }
}
