package com.sitas.checkin.services.boardingpass.service;


import com.sitas.checkin.domain.model.user.BoardingPass;
import com.sitas.checkin.domain.repository.user.IBoardingPassRepository;
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

import java.util.Optional;

/**
 * Service class for managing boarding passes.
 */
@Service
public class BoardingPassServiceImpl implements IBoardingPassService{

    @Autowired
    private IBoardingPassRepository repository;

    /**
     * Creates a new boarding pass.
     *
     * @param boardingPass The boarding pass to create.
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
    public ResponseEntity<BoardingPass> createBoardingPass(BoardingPass boardingPass) {
        try {
            // Get the passenger ID from the boarding pass
            // int passengerId = boardingPass.getPassenger().getPassengerId();
            // Check if a boarding pass with the given ID already exists
            // Boarding pass does not exist, proceed to create
            BoardingPass createdBoardingPass = repository.save(boardingPass);
            return ResponseEntity.ok(createdBoardingPass);

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
            Optional<BoardingPass> optionalBoardingPass = repository.findById(passengerId);
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
