package com.sitas.checkin.domain.model.user;

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

    @JoinColumn(name = "passenger_id", nullable = false)
    @NonNull
    private Integer passenger;

    @JoinColumn(name = "booking_id", nullable = false)
    @NonNull
    private Integer booking;

    @JoinColumn(name = "Flight_ID", nullable = false)
    @NonNull
    private Integer flight;

    /**
     * Medical information associated with this boarding pass.
     * Represents a many-to-one relationship with the MedicalInfo entity.
     */
    @ManyToOne
    @JoinColumn(name = "MEDICAL_INFO_ID", nullable = false)
    @NonNull
    private MedicalInfo medicalInfo;

    /**
     * Luggage information associated with this boarding pass.
     * Represents a many-to-one relationship with the LuggageInfo entity.
     */
    @ManyToOne
    @JoinColumn(name = "LUGGAGE_INFO_ID", nullable = false)
    @NonNull
    private LuggageInfo luggageInfo;

    /**
     * Timestamp indicating the boarding time.
     * This column is mapped to the "BOARDING_TIME" column in the database.
     */
    @Column(name = "BOARDING_TIME", nullable = false)
    @NonNull
    private Timestamp boardingTime;
}
