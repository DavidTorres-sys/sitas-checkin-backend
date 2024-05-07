package com.sitas.checkin.domain.model.user;

import com.sitas.checkin.domain.model.airline.Flight;
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
@Table(name = "BOARDING_PASS")
public class BoardingPass {

    /**
     * Unique identifier for Boarding Pass.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARDING_PASS_ID")
    private Integer boardingPassId;

    /**
     * Passenger associated with this boarding pass.
     * Represents a many-to-one relationship with the Passenger entity.
     */
    @ManyToOne
    @JoinColumn(name = "passenger_id", referencedColumnName = "passenger_id")
    private Passenger passenger;

    /**
     * Booking associated with this boarding pass.
     * Represents a many-to-one relationship with the Booking entity.
     */
    @ManyToOne
    @JoinColumn(name = "booking_id", referencedColumnName = "booking_id")
    private Booking booking;

    /**
     * Flight associated with this boarding pass.
     * Represents a many-to-one relationship with the Flight entity.
     */
    @ManyToOne
    @JoinColumn(name = "Flight_ID", referencedColumnName = "Flight_ID")
    private Flight flight;

    /**
     * Medical information associated with this boarding pass.
     * Represents a many-to-one relationship with the MedicalInfo entity.
     */
    @ManyToOne
    @JoinColumn(name = "MEDICAL_INFO_ID", referencedColumnName = "MEDICAL_INFO_ID")
    private MedicalInfo medicalInfo;

    /**
     * Luggage information associated with this boarding pass.
     * Represents a many-to-one relationship with the LuggageInfo entity.
     */
    @ManyToOne
    @JoinColumn(name = "LUGGAGE_INFO_ID", referencedColumnName = "LUGGAGE_INFO_ID")
    private LuggageInfo luggageInfo;

    /**
     * Timestamp indicating the boarding time.
     * This column is mapped to the "BOARDING_TIME" column in the database.
     */
    @Column(name = "BOARDING_TIME", nullable = false)
    @NonNull
    private Timestamp boardingTime;
}
