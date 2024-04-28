package com.sitas.checkin.domain.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) representing luggage information related to a user.
 * This DTO is used for transferring luggage information between layers of the application.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LuggageInfoDTO {

    /**
     * Unique identifier for the luggage information.
     */
    private int luggageInfoId;

    /**
     * Shipping address for the luggage.
     * Non-null field.
     * Must not be empty and must be within size constraints.
     * Should not contain any special characters.
     */
    @NotBlank(message = "Shipping address cannot be blank")
    @Size(min = 1, max = 150, message = "Shipping address must be between 1 and 150 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Shipping address must not contain special characters")
    private String shippingAddress;

    /**
     * Identifier for the luggage.
     */
    private int luggageId;
}