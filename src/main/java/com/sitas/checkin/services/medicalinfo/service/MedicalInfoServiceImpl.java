package com.sitas.checkin.services.medicalinfo.service;

import com.sitas.checkin.domain.model.user.MedicalInfo;
import com.sitas.checkin.persistance.repository.user.IMedicalInfoRepository;
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

@Service
public class MedicalInfoServiceImpl implements IMedicalInfoService{

    private final IMedicalInfoRepository medicalInfoRepository;

    /**
     * Constructs a new instance of the MedicalInfoServiceImpl class with the provided medical info repository.
     *
     * @param medicalInfoRepository The repository used for accessing medical information data.
     */
    @Autowired
    public MedicalInfoServiceImpl(IMedicalInfoRepository medicalInfoRepository) {
        this.medicalInfoRepository = medicalInfoRepository;
    }


    /**
     * Creates a new medical information record.
     *
     * @param medicalInfo The medical information to create.
     * @return ResponseEntity containing the created medical information if successful,
     * or an appropriate error response if the operation fails.
     */
    @Override
    public ResponseEntity<MedicalInfo> createMedicalInfo(MedicalInfo medicalInfo) {
        try {
            Optional<MedicalInfo> optionalMedicalInfo = this.medicalInfoRepository.findById(medicalInfo.getMedicalInfoId());
            if (optionalMedicalInfo.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            } else {
                MedicalInfo saveMedicalInfo = this.medicalInfoRepository.save(medicalInfo);
                return new ResponseEntity<>(saveMedicalInfo, HttpStatus.CREATED);
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
     * Retrieves medical information by its ID.
     *
     * @param medicalInfoId The ID of the medical information to retrieve.
     * @return ResponseEntity containing the retrieved medical information if found,
     * or an appropriate error response if the information is not found.
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<MedicalInfo> getMedicalInfo(Integer medicalInfoId) {
        try {
            Optional<MedicalInfo> optionalMedicalInfoId = medicalInfoRepository.findById(medicalInfoId);
            if (optionalMedicalInfoId.isPresent()) {
                // Luggage info found, return it
                MedicalInfo medicalInfo = optionalMedicalInfoId.get();
                return ResponseEntity.ok(medicalInfo);
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
     * Updates existing medical information with the provided data.
     *
     * @param medicalInfoId The ID of the medical information to update.
     * @param medicalInfo   The updated medical information.
     * @return ResponseEntity containing the updated medical information if successful,
     * or an appropriate error response if the operation fails.
     */
    @Override
    public ResponseEntity<MedicalInfo> putMedicalInfo(Integer medicalInfoId, MedicalInfo medicalInfo) {
        try {
            Optional<MedicalInfo> optionalMedicalInfo = medicalInfoRepository.findById(medicalInfoId);
            if (optionalMedicalInfo.isPresent()) {
                MedicalInfo existingLuggageInfo = optionalMedicalInfo.get();
                existingLuggageInfo.setMedicalInfoId(medicalInfoId);
                existingLuggageInfo.setContactPhone(medicalInfo.getContactPhone());
                existingLuggageInfo.setMedicalConditions(medicalInfo.getMedicalConditions());
                existingLuggageInfo.setContactName(medicalInfo.getContactName());
                existingLuggageInfo.setPersonId(medicalInfo.getPersonId());
                MedicalInfo createdMedicalInfo = this.medicalInfoRepository.save(existingLuggageInfo);
                return ResponseEntity.ok(createdMedicalInfo);
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
        } catch (Throwable e) {
            // Handle unexpected errors
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve luggage info", e);
        }
    }

    /**
     * Deletes medical information by its ID.
     *
     * @param medicalInfoId The ID of the medical information to delete.
     * @return ResponseEntity indicating the outcome of the deletion operation.
     * If the medical information is deleted successfully, returns ResponseEntity
     * with status 200 (OK) and a success message. If the medical information is not found,
     * returns ResponseEntity with status 404 (Not Found). If an unexpected error occurs,
     * returns ResponseEntity with status 500 (Internal Server Error) and an error message.
     */
    @Override
    public ResponseEntity<String> deleteMedicalInfo(Integer medicalInfoId) {
        try {
            Optional<MedicalInfo> optionalMedicalInfo = medicalInfoRepository.findById(medicalInfoId);
            if (optionalMedicalInfo.isPresent()) {
                medicalInfoRepository.deleteById(medicalInfoId);
                return ResponseEntity.ok("Medical information deleted correctly");
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
