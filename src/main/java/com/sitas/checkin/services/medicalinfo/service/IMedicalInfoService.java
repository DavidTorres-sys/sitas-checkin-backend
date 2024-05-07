package com.sitas.checkin.services.medicalinfo.service;

import com.sitas.checkin.domain.model.user.MedicalInfo;
import org.springframework.http.ResponseEntity;

/**
 * Service interface for managing medical info.
 */
public interface IMedicalInfoService {

    /**
     * Creates a new medical information record.
     *
     * @param medicalInfo The medical information to create.
     * @return ResponseEntity containing the created medical information if successful,
     *         or an appropriate error response if the operation fails.
     */
    public ResponseEntity<MedicalInfo> createMedicalInfo(MedicalInfo medicalInfo);

    /**
     * Retrieves medical information by its ID.
     *
     * @param medicalInfoId The ID of the medical information to retrieve.
     * @return ResponseEntity containing the retrieved medical information if found,
     *         or an appropriate error response if the information is not found.
     */
    public ResponseEntity<MedicalInfo> getMedicalInfo(Integer medicalInfoId);

    /**
     * Updates existing medical information with the provided data.
     *
     * @param medicalInfoId The ID of the medical information to update.
     * @param medicalInfo   The updated medical information.
     * @return ResponseEntity containing the updated medical information if successful,
     *         or an appropriate error response if the operation fails.
     */
    public ResponseEntity<MedicalInfo> putMedicalInfo(Integer medicalInfoId, MedicalInfo medicalInfo);

    /**
     * Deletes medical information by its ID.
     *
     * @param medicalInfoId The ID of the medical information to delete.
     * @return ResponseEntity indicating the outcome of the deletion operation.
     *         If the medical information is deleted successfully, returns ResponseEntity
     *         with status 200 (OK) and a success message. If the medical information is not found,
     *         returns ResponseEntity with status 404 (Not Found). If an unexpected error occurs,
     *         returns ResponseEntity with status 500 (Internal Server Error) and an error message.
     */
    public ResponseEntity<String> deleteMedicalInfo(Integer medicalInfoId);
}
