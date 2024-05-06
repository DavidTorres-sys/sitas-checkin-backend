package com.sitas.checkin.domain.model.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;
import lombok.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@Schema(description = "Booking info")
@Table(name = "Booking")
public class Booking {
    /**
     * Unique identifier for booking.
     */
    @Schema
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;

    /**
     * Identifier of the flight associated with this booking.
     */
    @Schema
    @Column(name = "flight_id", nullable = false)
    @NonNull
    private Long flightId;

    /**
     * Date and time when the booking was made.
     */
    @Schema
    @Column(name = "booking_date", nullable = false)
    @NonNull
    private Timestamp bookingDate;

    /**
     * Status of the booking.
     */
    @Schema
    @Column(name = "booking_status", nullable = false)
    @NotBlank(message = "Booking status cannot be blank")
    @Size(max = 20, message = "Booking status must not exceed 20 characters")
    @NonNull
    private String bookingStatus;

    /**
     * Total price of the booking.
     */
    @Schema
    @Column(name = "total_price", nullable = false)
    @DecimalMin(value = "0.01", message = "Total price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Invalid total price format")
    @NonNull
    private BigDecimal totalPrice;

}
