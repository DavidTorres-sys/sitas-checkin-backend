package com.sitas.checkin.services.luggageinfo.service;
import com.sitas.checkin.domain.model.user.LuggageInfo;
import com.sitas.checkin.persistance.repository.user.ILuggageInfoRepository;
import com.sitas.checkin.utils.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * Service class implementing ILuggageInfoService interface to manage luggage information.
 */
@Service
public class LuggageInfoServiceImpl implements ILuggageInfoService {


    private final ILuggageInfoRepository luggageInfoRepository;

    /**
     * Constructs a new instance of the LuggageInfoServiceImpl class with the provided luggage info repository.
     *
     * @param luggageInfoRepository The repository used for accessing luggage information data.
     */
    @Autowired
    public LuggageInfoServiceImpl(ILuggageInfoRepository luggageInfoRepository) {
        this.luggageInfoRepository = luggageInfoRepository;
    }

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
            Optional<LuggageInfo> optionalLuggageInfo = luggageInfoRepository.findById(luggageInfo.getLuggageInfoId());
            if (optionalLuggageInfo.isPresent()) {
                // Luggage info already exists, return a 409 Conflict response
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            } else {
                // Save the new luggage info
                LuggageInfo savedLuggageInfo = luggageInfoRepository.save(luggageInfo);
                return  new ResponseEntity<>(savedLuggageInfo, HttpStatus.CREATED);
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
            Optional<LuggageInfo> optionalLuggageInfo = luggageInfoRepository.findById(luggageInfoId);
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

    /**
     * Delete luggage information by ID.
     *
     * @param luggageInfoId The ID of the luggage information to retrieve.
     * @return String with a message if delete correctly, else returns a 404 Not Found response.
     * @throws BusinessException         If there is a data integrity violation or a database error.
     * @throws IllegalArgumentException If an invalid argument is provided.
     * @throws ResponseStatusException   If there is an unexpected error.
     */
    @Override
    public ResponseEntity<String> deleteLuggageInfo(Integer luggageInfoId) {
        try {
            Optional<LuggageInfo> optionalLuggageInfo = luggageInfoRepository.findById(luggageInfoId);
            if (optionalLuggageInfo.isPresent()) {
                luggageInfoRepository.deleteById(luggageInfoId);
                return ResponseEntity.ok("Luggage information deleted correctly");
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

    @Override
    public ResponseEntity<List<LuggageInfo>> getAllLuggageInfo() {
        return null;
    }

    /**
     * Updates the information of a luggage item with the given ID.
     *
     * @param luggageInfoId The ID of the luggage item to update.
     * @param luggageInfo   The updated luggage information.
     * @return ResponseEntity containing the updated luggage information with a 200 OK response
     *         if successful, or a 404 Not Found response if the luggage item with the specified ID
     *         does not exist.
     * @throws BusinessException        If there is a data integrity violation or a database error occurs.
     * @throws IllegalArgumentException If the provided luggage ID is invalid.
     * @throws ResponseStatusException   If an unexpected error occurs.
     */
    @Override
    public ResponseEntity<LuggageInfo> putLuggageInfo(Integer luggageInfoId, LuggageInfo luggageInfo) {
        try {
            Optional<LuggageInfo> optionalLuggageInfo = luggageInfoRepository.findById(luggageInfoId);
            if (optionalLuggageInfo.isPresent()) {
                LuggageInfo existingLuggageInfo = optionalLuggageInfo.get();
                // Update the existing luggage info with the new information
                existingLuggageInfo.setLuggageInfoId(luggageInfoId);
                existingLuggageInfo.setLuggageId(luggageInfo.getLuggageId());
                existingLuggageInfo.setShippingAddress(luggageInfo.getShippingAddress());
                LuggageInfo luggageInfoUpdated = luggageInfoRepository.save(existingLuggageInfo);
                return ResponseEntity.ok(luggageInfoUpdated);
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

