package com.sitas.checkin.controller.v1;

import com.sitas.checkin.domain.model.user.LuggageInfo;
import com.sitas.checkin.services.luggageinfo.service.ILuggageInfoService;
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
 * Controller class for managing luggage info operations.
 */
@RestController
@RequestMapping("/v1/luggage-info")
public class LuggageInfoController {

    private final ILuggageInfoService luggageInfoService;

    /**
     * Constructs a new ILuggageInfoService with the provided luggage info service.
     *
     * @param luggageInfoService The repository for handling boarding pass data access operations.
     */
    @Autowired
    public LuggageInfoController(ILuggageInfoService luggageInfoService) {
        this.luggageInfoService = luggageInfoService;
    }

    /**
     * Saves luggage information for a passenger.
     *
     * @param luggageInfo The luggage information object to be created.
     * @return ResponseEntity containing the created luggage information with a 201 Created response
     *         if successful, or a 400 Bad Request response if the request is invalid,
     *         a 409 Conflict response if the luggage information already exists, or
     *         a 500 Internal Server Error response if an unexpected error occurs.
     * @throws BusinessException        If there is a data integrity violation or a database error occurs.
     * @throws IllegalArgumentException If the provided luggage information is invalid.
     * @throws ResponseStatusException   If an unexpected error occurs.
     */
    @Operation(summary = "save a luggage info of a passager")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Luggage Info created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = LuggageInfo.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponse.class)) }),
        @ApiResponse(responseCode = "409", description = "Conflict - Luggage Info already exists", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponse.class)) }),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RuntimeException.class)) })
    })
    @PostMapping("/add-luggage-info")
    public ResponseEntity<LuggageInfo> addLuggageInfo(
        @Parameter(description = "Luggage info object to be created", required = true)
        @RequestBody LuggageInfo luggageInfo
    ) {
        try {
            return luggageInfoService.saveLuggageInfo(luggageInfo);
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
    @Operation(summary = "retrieve luggage info of a passager")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retrieve Luggage Info", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = LuggageInfo.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponse.class)) }),
        @ApiResponse(responseCode = "404", description = "Luggage Info not found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponse.class)) }),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RuntimeException.class)) })
    })
    @GetMapping("/get-luggage-info/{luggageInfoId}")
    public ResponseEntity<LuggageInfo> getLuggageInfo(
        @Parameter(description = "The ID of the luggage information to retrieve", example = "123")
        @PathVariable Integer luggageInfoId) {
        try {
            return luggageInfoService.getLuggageInfo(luggageInfoId);
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
     * @param luggageInfoId The ID of the luggage information to delete.
     * @return ResponseEntity with luggage information if found, else returns a 404 Not Found response.
     * @throws BusinessException         If there is a data integrity violation or a database error.
     * @throws IllegalArgumentException If an invalid argument is provided.
     * @throws ResponseStatusException   If there is an unexpected error.
     */
    @Operation(summary = "delete luggage info of a passager by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "deleted Luggage Info", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = LuggageInfo.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponse.class)) }),
        @ApiResponse(responseCode = "404", description = "Luggage Info not found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponse.class)) }),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RuntimeException.class)) })
    })
    @DeleteMapping("/delete-luggage-info/{luggageInfoId}")
    public ResponseEntity<String> deleteLuggageInfo(
        @Parameter(description = "The ID of the luggage information to delete", example = "123")
        @PathVariable Integer luggageInfoId
    ) {
        try {
            return luggageInfoService.deleteLuggageInfo(luggageInfoId);
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
     * Updates luggage information of a passenger by ID.
     *
     * @param luggageInfoId The ID of the luggage information to update.
     * @param luggageInfo   The luggage information object with updated details.
     * @return ResponseEntity containing the updated luggage information with a 200 OK response
     *         if successful, or a 400 Bad Request response if the request is invalid,
     *         a 404 Not Found response if the luggage information with the specified ID
     *         does not exist, or a 500 Internal Server Error response if an unexpected error occurs.
     * @throws BusinessException        If there is a data integrity violation or a database error occurs.
     * @throws IllegalArgumentException If the provided luggage information ID is invalid.
     * @throws ResponseStatusException   If an unexpected error occurs.
     */
    @Operation(summary = "Updates luggage info of a passager")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "deleted Luggage Info", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = LuggageInfo.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponse.class)) }),
        @ApiResponse(responseCode = "404", description = "Luggage Info not found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponse.class)) }),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RuntimeException.class)) })
    })
    @PutMapping("/put-luggage-info/{luggageInfoId}")
    public ResponseEntity<LuggageInfo> putLuggageInfo(
        @Parameter(description = "The ID of the luggage information to update", example = "123", required = true)
        @PathVariable Integer luggageInfoId,
        @Parameter(description = "Updated luggage information", required = true)
        @RequestBody LuggageInfo luggageInfo
    ) {
        try {
            return luggageInfoService.putLuggageInfo(luggageInfoId, luggageInfo);
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
