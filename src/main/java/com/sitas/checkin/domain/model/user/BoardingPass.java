package com.sitas.checkin.domain.model.user;

import com.sitas.checkin.domain.model.airline.Flight;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import lombok.*;

import java.sql.Timestamp;

/**
 * Represents Boording Pass information.
 * This class is mapped to the "BOARDING_PASS" table in the database.
 */
@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@Schema(description = "Información del boarding pass")
@Table(name = "BOARDING_PASS")
public class BoardingPass {

    /**
     * Unique identifier for Boarding Pass.
     */
    @Schema
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARDING_PASS_ID")
    private Integer boardingPassId;

    /**
     * Passenger associated with this boarding pass.
     * Represents a many-to-one relationship with the Passenger entity.
     */
    @Schema
    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    @NonNull
    private Passenger passenger;

    /**
     * Booking associated with this boarding pass.
     * Represents a many-to-one relationship with the Booking entity.
     */
    @Schema
    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    @NonNull
    private Booking booking;

    /**
     * Flight associated with this boarding pass.
     * Represents a many-to-one relationship with the Flight entity.
     */
    @Schema
    @ManyToOne
    @JoinColumn(name = "Flight_ID", nullable = false)
    @NonNull
    private Flight flight;

    /**
     * Medical information associated with this boarding pass.
     * Represents a many-to-one relationship with the MedicalInfo entity.
     */
    @Schema
    @ManyToOne
    @JoinColumn(name = "MEDICAL_INFO_ID", nullable = false)
    @NonNull
    private MedicalInfo medicalInfo;

    /**
     * Luggage information associated with this boarding pass.
     * Represents a many-to-one relationship with the LuggageInfo entity.
     */
    @Schema
    @ManyToOne
    @JoinColumn(name = "LUGGAGE_INFO_ID", nullable = false)
    @NonNull
    private LuggageInfo luggageInfo;

    /**
     * Timestamp indicating the boarding time.
     * This column is mapped to the "BOARDING_TIME" column in the database.
     */
    @Schema
    @Column(name = "BOARDING_TIME", nullable = false)
    @NonNull
    private Timestamp boardingTime;
}
