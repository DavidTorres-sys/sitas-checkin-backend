package com.sitas.checkin.controller.v1;

import com.sitas.checkin.domain.model.user.BoardingPass;
import com.sitas.checkin.services.boardingpass.service.IBoardingPassService;
import com.sitas.checkin.utils.common.StandardResponse;
import com.sitas.checkin.utils.exception.BusinessException;
import com.sitas.checkin.utils.exception.DataDuplicatedException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controller class for managing boarding pass operations.
 */
@RestController
@RequestMapping("/v1/boarding-pass")
@CrossOrigin("*")
@Tag(name = "modulo de checkin", description = "Operaciones de checkin")
public class BoardingPassController {

    private final IBoardingPassService boardingPassService;

    /**
     * Constructs a new BoardingPassService with the provided boarding pass repository.
     *
     * @param boardingPassService The repository for handling boarding pass data access operations.
     */
    @Autowired
    public BoardingPassController(IBoardingPassService boardingPassService) {
        this.boardingPassService = boardingPassService;
    }

    /**
     * Endpoint to create a new boarding pass.
     *
     * @param lastName     The last name of the passenger.
     * @param flightNumber The flight number.
     * @return ResponseEntity containing the created boarding pass if successful (HTTP status 200),
     *         or an appropriate error response otherwise (HTTP status 409 if the boarding pass already exists,
     *         or HTTP status 500 for internal server error).
     * @throws ResponseStatusException If an error occurs during the operation.
     */
    @Operation(summary = "Create a boarding pass")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Boarding pass created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = BoardingPass.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponse.class)) }),
        @ApiResponse(responseCode = "409", description = "Conflict - Boarding pass already exists", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponse.class)) }),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RuntimeException.class)) })
    })
    @PostMapping
    public ResponseEntity<BoardingPass> createBoardingPass(
        @Parameter(description = "Boarding pass object to be created", required = true)
        @RequestParam("lastName") String lastName,
        @RequestParam("flightNumber") String flightNumber) {
        try {
            return boardingPassService.createBoardingPass(lastName, flightNumber);
        } catch (DataDuplicatedException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        } catch (BusinessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create boarding pass", e);
        }
    }
}