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
     * @throws BusinessException         If there is a data integrity violation or a database error.
     * @throws IllegalArgumentException If an invalid argument is provided.
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
    @PostMapping("/save-boarding-pass")
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

    /**
     * Retrieves a boarding pass by ID.
     *
     * @param boardingPassId The ID of the boarding pass to retrieve.
     * @return ResponseEntity containing the retrieved boarding pass if found,
     *         else returns an appropriate error response.
     * @throws BusinessException         If there is a data integrity violation or a database error.
     * @throws IllegalArgumentException If an invalid argument is provided.
     * @throws ResponseStatusException   If there is an unexpected error.
     */
    @Operation(summary = "get a boarding pass")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retrieve Boarding pass", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = BoardingPass.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponse.class)) }),
        @ApiResponse(responseCode = "404", description = "Boarding pass not found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponse.class)) }),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RuntimeException.class)) })
    })
    @GetMapping("/get-boarding-pass/{boardingPassId}")
    public ResponseEntity<BoardingPass> getBoardingPass (
        @Parameter(description = "The ID of the boarding pass information to retrieve", example = "123")
        @PathVariable  Integer boardingPassId
    ) {
        try {
            return boardingPassService.getBoardingPass(boardingPassId);
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

    /**
     * Retrieves a boarding pass by ID.
     *
     * @param passengerId The ID of the boarding pass to retrieve.
     * @return ResponseEntity containing the retrieved boarding pass if found,
     *         else returns an appropriate error response.
     * @throws BusinessException         If there is a data integrity violation or a database error.
     * @throws IllegalArgumentException If an invalid argument is provided.
     * @throws ResponseStatusException   If there is an unexpected error.
     */
    @Operation(summary = "get a boarding pass by passenger id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retrieve Boarding pass", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = BoardingPass.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponse.class)) }),
        @ApiResponse(responseCode = "404", description = "Boarding pass not found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponse.class)) }),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RuntimeException.class)) })
    })
    @GetMapping("/get-boarding-pass/{passengerId}")
    public ResponseEntity<BoardingPass> getBoardingPassByPassenger (
        @Parameter(description = "The ID of the passenger associated to a boarding pass to retrieve", example = "123")
        @PathVariable Integer passengerId
    ) {
        try {
            return boardingPassService.getBoardingPassByPassenger(passengerId);
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
    @Operation(summary = "delete a boarding pass by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Boarding pass deleted correctly", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = BoardingPass.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponse.class)) }),
        @ApiResponse(responseCode = "404", description = "Boarding pass not found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponse.class)) }),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RuntimeException.class)) })
    })
    @DeleteMapping("/delete-boarding-pass/{boardingPassId}")
    public ResponseEntity<String> deleteBoardingPass(
        @Parameter(description = "The ID of the boarding pass to delete", example = "123")
        @PathVariable Integer boardingPassId
    ) {
        try {
            return boardingPassService.deleteBoardingPass(boardingPassId);
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