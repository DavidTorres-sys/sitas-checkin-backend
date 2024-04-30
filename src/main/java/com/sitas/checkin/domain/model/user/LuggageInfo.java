package com.sitas.checkin.domain.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


/**
 * Represents luggage information related to a user.
 * This class is mapped to the "LUGGAGE_INFO" table in the database.
 */
@Entity
@Data
@NoArgsConstructor
@Table (name = "LUGGAGE_INFO")
public class LuggageInfo {
    /**
     * Unique identifier for luggage information.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer luggageInfoId;

    /**
     * Shipping address for the luggage.
     * Non-null field.
     * Must not be empty and must be within size constraints.
     * Should not contain any special characters.
     */
    @Column(name = "SHIPPING_ADDRESS", nullable = false)
    @NotBlank(message = "Shipping address cannot be blank")
    @Size(min = 1, max = 150, message = "Shipping address must be between 1 and 150 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Shipping address must not contain special characters")
    @NonNull
    private String shippingAddress;

    /**
     * Identifier for the luggage.
     */
    @Column (name = "LUGGAGE_ID")
    private Integer luggageId;
}
