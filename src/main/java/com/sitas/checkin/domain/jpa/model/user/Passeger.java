package com.sitas.checkin.domain.jpa.model.user;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents Passeger.
 * This class is mapped to the "Passeger" table in the database.
 */
@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@Table(name = "Passenger")
public class Passeger {

    /**
     * Unique identifier for passenger.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private Long passengerId;

    /**
     * Identifier of the person associated with this passenger.
     */
    @Column(name = "person_id")
    @NonNull
    private Long personId;

    /**
     * Identifier of the booking associated with this passenger.
     */
    @Column(name = "booking_id")
    @NonNull
    private Long bookingId;
}
