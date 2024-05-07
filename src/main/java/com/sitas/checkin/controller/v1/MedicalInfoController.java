package com.sitas.checkin.controller.v1;


import com.sitas.checkin.domain.model.user.LuggageInfo;
import com.sitas.checkin.domain.model.user.MedicalInfo;
import com.sitas.checkin.services.medicalinfo.service.IMedicalInfoService;
import com.sitas.checkin.utils.common.StandardResponse;
import com.sitas.checkin.utils.exception.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controller class for managing medical information.
 * Handles HTTP requests related to medical information management.
 */
@RestController
@RequestMapping("/v1/medical-info")
public class MedicalInfoController {

    private final IMedicalInfoService medicalInfoService;

    /**
     * Constructs a new instance of the MedicalInfoController class with the provided medical info service.
     *
     * @param medicalInfoService The service responsible for handling medical information operations.
     */
    @Autowired
    public MedicalInfoController(IMedicalInfoService medicalInfoService) {
        this.medicalInfoService = medicalInfoService;
    }

    /**
     * Saves Medical information for a passenger.
     *
     * @param medicalInfo The Medical information object to be created.
     * @return ResponseEntity containing the created Medical information with a 201 Created response
     *         if successful, or a 400 Bad Request response if the request is invalid,
     *         a 409 Conflict response if the Medical information already exists, or
     *         a 500 Internal Server Error response if an unexpected error occurs.
     * @throws BusinessException        If there is a data integrity violation or a database error occurs.
     * @throws IllegalArgumentException If the provided Medical information is invalid.
     * @throws ResponseStatusException   If an unexpected error occurs.
     */
    @Operation(summary = "save a medical info of a passenger")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Medical information created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = LuggageInfo.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponse.class)) }),
        @ApiResponse(responseCode = "409", description = "Conflict - Medical information already exists", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponse.class)) }),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RuntimeException.class)) })
    })
    @PostMapping("/add-medical-info")
    public ResponseEntity<MedicalInfo> addMedicalInfo(
        @Parameter(description = "Medical information object to be created", required = true)
        @RequestBody MedicalInfo medicalInfo
    ) {
        try {
            return medicalInfoService.createMedicalInfo(medicalInfo);
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
     * @param medicalInfoId The ID of the medical information to retrieve.
     * @return ResponseEntity with medical information if found, else returns a 404 Not Found response.
     * @throws BusinessException         If there is a data integrity violation or a database error.
     * @throws IllegalArgumentException If an invalid argument is provided.
     * @throws ResponseStatusException   If there is an unexpected error.
     */
    @Operation(summary = "retrieve medical information of a passenger")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retrieve medical information", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = LuggageInfo.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponse.class)) }),
        @ApiResponse(responseCode = "404", description = "Medical information not found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponse.class)) }),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RuntimeException.class)) })
    })
    @GetMapping("/get-medical-info/{medicalInfoId}")
    public ResponseEntity<MedicalInfo> getMedicalInfo(
        @Parameter(description = "The ID of the medical information to retrieve", example = "123")
        @PathVariable Integer medicalInfoId) {
        try {
            return medicalInfoService.getMedicalInfo(medicalInfoId);
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
     * @param medicalInfoId The ID of the medical information to delete.
     * @return ResponseEntity with medical information if found, else returns a 404 Not Found response.
     * @throws BusinessException         If there is a data integrity violation or a database error.
     * @throws IllegalArgumentException If an invalid argument is provided.
     * @throws ResponseStatusException   If there is an unexpected error.
     */
    @Operation(summary = "delete medical information of a passenger by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "deleted medical information", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = LuggageInfo.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponse.class)) }),
        @ApiResponse(responseCode = "404", description = "Medical information not found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponse.class)) }),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RuntimeException.class)) })
    })
    @DeleteMapping("/delete-medical-info/{medicalInfoId}")
    public ResponseEntity<String> deleteMedicalInfo(
        @Parameter(description = "The ID of the medical information to delete", example = "123")
        @PathVariable Integer medicalInfoId
    ) {
        try {
            return medicalInfoService.deleteMedicalInfo(medicalInfoId);
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
     * Updates medical information of a passenger by ID.
     *
     * @param medicalInfoId The ID of the medical information to update.
     * @param medicalInfo   The medical information object with updated details.
     * @return ResponseEntity containing the updated medical information with a 200 OK response
     *         if successful, or a 400 Bad Request response if the request is invalid,
     *         a 404 Not Found response if the medical information with the specified ID
     *         does not exist, or a 500 Internal Server Error response if an unexpected error occurs.
     * @throws BusinessException        If there is a data integrity violation or a database error occurs.
     * @throws IllegalArgumentException If the provided luggage information ID is invalid.
     * @throws ResponseStatusException   If an unexpected error occurs.
     */
    @Operation(summary = "Updates medical information of a passenger")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "deleted medical information", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = LuggageInfo.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponse.class)) }),
        @ApiResponse(responseCode = "404", description = "Medical information not found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponse.class)) }),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RuntimeException.class)) })
    })
    @PutMapping("/put-medical-info/{medicalInfoId}")
    public ResponseEntity<MedicalInfo> putMedicalInfo(
        @Parameter(description = "The ID of the luggage information to update", example = "123", required = true)
        @PathVariable Integer medicalInfoId,
        @Parameter(description = "Updated luggage information", required = true)
        @RequestBody MedicalInfo medicalInfo
    ) {
        try {
            return medicalInfoService.putMedicalInfo(medicalInfoId, medicalInfo);
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
