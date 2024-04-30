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
     * Retrieves luggage information by ID.
     *
     * @param luggageInfo The ID of the luggage information to retrieve.
     * @return ResponseEntity with luggage information if found, else returns a 404 Not Found response.
     * @throws BusinessException         If there is a data integrity violation or a database error.
     * @throws IllegalArgumentException If an invalid argument is provided.
     * @throws ResponseStatusException   If there is an unexpected error.
     */
    @Operation(summary = "save a luggage info of a passager")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Luggage Info created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = LuggageInfo.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponse.class)) }),
        @ApiResponse(responseCode = "409", description = "Conflict - Boarding pass already exists", content = {
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
        @ApiResponse(responseCode = "200", description = "retrieve Luggage Info", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = LuggageInfo.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponse.class)) }),
        @ApiResponse(responseCode = "409", description = "Conflict - Boarding pass already exists", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponse.class)) }),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RuntimeException.class)) })
    })
    @GetMapping("/get-luggage-info/{luggageInfoId}")
    public ResponseEntity<LuggageInfo> getLuggageInfo(
        @Parameter(description = "The ID of the luggage information to retrieve", example = "123")
        @RequestParam Integer luggageInfoId
    ) {
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
}
