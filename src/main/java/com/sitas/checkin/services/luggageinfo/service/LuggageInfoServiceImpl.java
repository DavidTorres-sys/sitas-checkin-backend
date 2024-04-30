package com.sitas.checkin.services.luggageinfo.service;
import com.sitas.checkin.domain.model.user.LuggageInfo;
import com.sitas.checkin.domain.repository.user.ILuggageInfoRepository;
import com.sitas.checkin.utils.exception.BusinessException;
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
 * Service class implementing ILuggageInfoService interface to manage luggage information.
 */
@Service
public class LuggageInfoServiceImpl implements ILuggageInfoService {

    /**
     * Dependency injection for ILuggageInfoRepository.
     */
    @Autowired
    private ILuggageInfoRepository repository;

    /**
     * Saves luggage information.
     *
     * @param luggageInfo The luggage information to be saved.
     * @return ResponseEntity with the saved luggage information if successfully saved,
     *         else returns a 409 Conflict response if luggage information already exists.
     * @throws BusinessException         If there is a data integrity violation or a database error.
     * @throws IllegalArgumentException If an invalid argument is provided.
     * @throws ResponseStatusException   If there is an unexpected error.
     */
    @Override
    public ResponseEntity<LuggageInfo> saveLuggageInfo(LuggageInfo luggageInfo) {
        try {
            Optional<LuggageInfo> optionalLuggageInfo = repository.findById(luggageInfo.getLuggageInfoId());
            if (optionalLuggageInfo.isPresent()) {
                // Luggage info already exists, return a 409 Conflict response
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            } else {
                // Save the new luggage info
                LuggageInfo savedLuggageInfo = repository.save(luggageInfo);
                return ResponseEntity.ok(savedLuggageInfo);
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
        } catch (Throwable e) {
            // Handle unexpected errors
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve luggage info", e);
        }
    }

    /**
     * Retrieves luggage information by ID.
     *
     * @param luggageInfoId The ID of the luggage information to retrieve.
     * @return ResponseEntity with luggage information if found, else returns a 404 Not Found response.
     * @throws BusinessException         If there is a data integrity violation or a database error.
     * @throws IllegalArgumentException If an invalid argument is provided.
     * @throws ResponseStatusException   If there is an unexpected error.
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<LuggageInfo> getLuggageInfo(Integer luggageInfoId) {
        try {
            Optional<LuggageInfo> optionalLuggageInfo = repository.findById(luggageInfoId);
            if (optionalLuggageInfo.isPresent()) {
                // Luggage info found, return it
                LuggageInfo luggageInfo = optionalLuggageInfo.get();
                return ResponseEntity.ok(luggageInfo);
            } else {
                // Luggage info not found, return a 404 Not Found response
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
        } catch (Throwable e) {
            // Handle unexpected errors
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve luggage info", e);
        }
    }
}

