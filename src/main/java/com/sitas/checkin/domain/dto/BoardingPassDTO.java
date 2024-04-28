package com.sitas.checkin.domain.dto;

import lombok.*;
import java.sql.Timestamp;

/**
 * Data Transfer Object (DTO) representing boarding pass information.
 * This DTO is used for transferring boarding pass information between layers of the application.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardingPassDTO {
    /**
     * Unique identifier for the boarding pass.
     */
    private Integer boardingPassId;

    /**
     * Identifier of the passenger associated with this boarding pass.
     */
    private Integer passengerId;

    /**
     * Identifier of the booking associated with this boarding pass.
     */
    private Integer bookingId;

    /**
     * Identifier of the flight associated with this boarding pass.
     */
    private Integer flightId;

    /**
     * Identifier of the medical information associated with this boarding pass.
     */
    private Integer medicalInfoId;

    /**
     * Identifier of the luggage information associated with this boarding pass.
     */
    private Integer luggageInfoId;

    /**
     * Timestamp indicating the boarding time.
     */
    private Timestamp boardingTime;
}
