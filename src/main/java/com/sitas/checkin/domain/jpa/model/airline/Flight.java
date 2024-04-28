package com.sitas.checkin.domain.jpa.model.airline;

import jakarta.validation.constraints.*;
import lombok.*;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@Table(name = "Flight")
public class Flight {

    /**
     * Unique identifier for the flight.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Long flightId;

    /**
     * Flight number.
     */
    @Column(name = "flight_number", nullable = false, length = 6)
    @NotBlank(message = "Flight number is required")
    @Size(min = 1, max = 6, message = "Flight number must be between 1 and 6 characters")
    private String flightNumber;

    /**
     * Base price of the flight.
     */
    @Column(name = "base_price", nullable = false, precision = 10, scale = 2)
    @DecimalMin(value = "0.01", message = "Base price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Invalid base price format")
    private BigDecimal basePrice;

    /**
     * Tax percentage applied to the base price.
     */
    @Column(name = "tax_percent", nullable = false, precision = 5, scale = 2)
    @DecimalMin(value = "0.01", message = "Tax percent must be greater than 0")
    @DecimalMax(value = "100.00", message = "Tax percent must be less than or equal to 100")
    @Digits(integer = 3, fraction = 2, message = "Invalid tax percent format")
    private BigDecimal taxPercent;

    /**
     * Surcharge applied to the base price.
     */
    @Column(name = "surcharge", nullable = false, precision = 10, scale = 2)
    @DecimalMin(value = "0.01", message = "Surcharge must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Invalid surcharge format")
    private BigDecimal surcharge;

    /**
     * Status of the flight.
     */
    @Column(name = "status", nullable = false, length = 20)
    private String status = "Scheduled";

}
