package com.sitas.checkin.services.boardingpass.service;


import com.sitas.checkin.domain.model.airline.Flight;
import com.sitas.checkin.domain.model.user.*;
import com.sitas.checkin.persistance.repository.airline.IFlightRepository;
import com.sitas.checkin.persistance.repository.user.*;
import com.sitas.checkin.utils.exception.BusinessException;
import com.sitas.checkin.utils.exception.DataDuplicatedException;
import com.sitas.checkin.utils.exception.DataNotFoundException;
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
 * Service class for managing boarding passes(Check-in).
 */
@Service
public class BoardingPassServiceImpl implements IBoardingPassService {

    private final IBoardingPassRepository boardingPassRepository;
    private final IPassengerRepository passengerRepository;
    private final IFlightRepository flightRepository;
    private final IPersonRepository personRepository;
    private final ILuggageInfoRepository luggageInfoRepository;
    private final IMedicalInfoRepository medicalInfoRepository;
    private final IBookingRepository bookingRepository;

    /**
     * Constructor of the BoardingPassServiceImpl class.
     * @param boardingPassRepository Boarding pass repository.
     * @param passengerRepository Passenger repository.
     * @param personRepository Person repository.
     * @param flightRepository Flight repository.
     * @param luggageInfoRepository Luggage info repository
     * @param medicalInfoRepository Medical info repository.
     * @param bookingRepository booking repository
     */
    @Autowired
    public BoardingPassServiceImpl(IBoardingPassRepository boardingPassRepository,
                                   ILuggageInfoRepository luggageInfoRepository,
                                   IMedicalInfoRepository medicalInfoRepository,
                                   IPassengerRepository passengerRepository,
                                   IPersonRepository personRepository,
                                   IFlightRepository flightRepository,
                                   IBookingRepository bookingRepository) {
        this.boardingPassRepository = boardingPassRepository;
        this.medicalInfoRepository = medicalInfoRepository;
        this.luggageInfoRepository = luggageInfoRepository;
        this.passengerRepository = passengerRepository;
        this.flightRepository = flightRepository;
        this.personRepository = personRepository;
        this.bookingRepository = bookingRepository;
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
     */
    @Override
    public ResponseEntity<BoardingPass> createBoardingPass(String lastName, String flightNumber) {
        try {

            // Find passenger by last name
            Person person = personRepository.findByLastName(lastName)
                .orElseThrow(() -> new DataNotFoundException("Person not found"));

            // Find flight by flight number
            Flight flight = flightRepository.findByFlightNumber(flightNumber)
                .orElseThrow(() -> new DataNotFoundException("Flight not found"));

            // Find passenger by id
            Passenger passenger = passengerRepository.findByPersonId(person.getPersonId())
                .orElseThrow(() -> new DataNotFoundException("Passenger not found"));

            // Find booking by id
            Booking booking = bookingRepository.findById(passenger.getBookingId())
                .orElseThrow(() -> new DataNotFoundException("Booking not found"));

            // Create luggage info
            LuggageInfo luggageInfo = createLuggageInfo();

            // Create medical info
            MedicalInfo medicalInfo = createMedicalInfo(person.getPersonId());

            // Create a new boarding pass
            BoardingPass boardingPass = createNewBoardingPass(passenger, flight, luggageInfo, medicalInfo, booking);

            return new ResponseEntity<>(boardingPass, HttpStatus.CREATED);
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
     * @param boardingPassId The ID of the boarding pass to retrieve.
     * @return ResponseEntity containing the retrieved boarding pass, or 404 Not Found if not found.
     * @throws BusinessException          If a data integrity violation or database error occurs.
     * @throws IllegalArgumentException  If an invalid argument is passed.
     * @throws ResponseStatusException    If an unexpected error occurs during the operation.
     */
    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<BoardingPass> getBoardingPass(Integer boardingPassId) {
        try {
            // Retrieve the boarding pass associated with the passenger ID
            Optional<BoardingPass> optionalBoardingPass = boardingPassRepository.findById(boardingPassId);
            // Check if the boarding pass exists
            if (optionalBoardingPass.isPresent()) {
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

    /**
     * Retrieves the boarding pass associated with a specific passenger ID.
     * This method is transactional and read-only.
     *
     * @param passengerId The unique identifier of the passenger.
     * @return A ResponseEntity containing either:
     *         - HTTP status 200 (OK) and the boarding pass associated with the provided passenger ID.
     *         - HTTP status 404 (Not Found) if no boarding pass is found for the provided passenger ID.
     * @throws BusinessException if there are data integrity violations or database access errors.
     * @throws IllegalArgumentException if an invalid argument is provided.
     * @throws ResponseStatusException if an unexpected error occurs.
     */
    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<BoardingPass> getBoardingPassByPassenger(Integer passengerId) {
        try {
            // Retrieve the boarding pass associated with the passenger ID
            Optional<BoardingPass> optionalBoardingPass = boardingPassRepository.findByPassengerPassengerId(passengerId);
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

    /**
     * Deletes a boarding pass by its ID.
     *
     * @param boardingPassId The ID of the boarding pass to delete.
     * @return ResponseEntity with a success message if the boarding pass is deleted successfully,
     *         or a ResponseEntity with status 404 if the boarding pass is not found.
     * @throws BusinessException if there is a data integrity violation or a database access error.
     * @throws IllegalArgumentException if the provided boarding pass ID is invalid.
     * @throws ResponseStatusException if an unexpected error occurs while deleting the boarding pass.
     */
    @Override
    public ResponseEntity<String> deleteBoardingPass(Integer boardingPassId) {
        try {
            Optional<BoardingPass> optionalBoardingPass = this.boardingPassRepository.findById(boardingPassId);
            if (optionalBoardingPass.isPresent()) {
                boardingPassRepository.deleteById(boardingPassId);
                return ResponseEntity.ok("Boarding pass deleted correctly");
            } else {
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

    /**
     * Creates a new luggage information object with default values.
     *
     * @return The created luggage information object.
     */
    private LuggageInfo createLuggageInfo() {
        LuggageInfo luggageInfo = new LuggageInfo();
        luggageInfo.setShippingAddress("Pendiente");
        luggageInfo.setLuggageId(0);
        return luggageInfoRepository.save(luggageInfo);
    }

    /**
     * Creates a new medical information object with default values.
     *
     * @param personId The ID of the person associated with the medical information.
     * @return The created medical information object.
     */
    private MedicalInfo createMedicalInfo(Integer personId) {
        MedicalInfo medicalInfo = new MedicalInfo();
        medicalInfo.setPersonId(personId);
        medicalInfo.setMedicalConditions("Pendiente");
        medicalInfo.setContactName("Pendiente");
        medicalInfo.setContactPhone("Pendiente");
        return medicalInfoRepository.save(medicalInfo);
    }

    /**
     * Creates a new boarding pass object.
     *
     * @param passenger   The passenger associated with the boarding pass.
     * @param flight      The flight associated with the boarding pass.
     * @param luggageInfo The luggage information associated with the boarding pass.
     * @param medicalInfo The medical information associated with the boarding pass.
     * @return The created boarding pass object.
     */
    private BoardingPass createNewBoardingPass(Passenger passenger,
                                               Flight flight,
                                               LuggageInfo luggageInfo,
                                               MedicalInfo medicalInfo,
                                               Booking booking) {
        BoardingPass boardingPass = new BoardingPass();
        boardingPass.setBooking(booking);
        boardingPass.setPassenger(passenger);
        boardingPass.setFlight(flight);
        boardingPass.setLuggageInfo(luggageInfo);
        boardingPass.setMedicalInfo(medicalInfo);
        boardingPass.setBoardingTime(new Timestamp(System.currentTimeMillis()));
        return boardingPassRepository.save(boardingPass);
    }

}
