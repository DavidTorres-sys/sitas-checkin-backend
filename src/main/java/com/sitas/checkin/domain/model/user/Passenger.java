package com.sitas.checkin.domain.model.user;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents Passenger.
 * This class is mapped to the "Passenger" table in the database.
 */
@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@Table(name = "Passenger")
public class Passenger {

    /**
     * Unique identifier for passenger.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private Integer passengerId;

    /**
     * Identifier of the person associated with this passenger.
     */
    @Column(name = "person_id")
    @NonNull
    private Integer personId;

    /**
     * Identifier of the booking associated with this passenger.
     */
    @Column(name = "booking_id")
    @NonNull
    private Integer bookingId;
}