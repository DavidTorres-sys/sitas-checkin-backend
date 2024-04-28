package com.sitas.checkin.domain.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) representing medical information related to a person.
 * This DTO is used for transferring medical information between layers of the application.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalInfoDTO {
    /**
     * Unique identifier for the medical information.
     */
    private Integer medicalInfoId;

    /**
     * Identifier of the person associated with this medical information.
     */
    private Integer personId;

    /**
     * Medical conditions of the person.
     * Must not be empty and must be within size constraints.
     * Should not contain any special characters.
     */
    @NotBlank(message = "Medical conditions must not be empty")
    @Size(min = 1, max = 150, message = "Medical conditions must be between 1 and 150 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Medical conditions must not contain special characters")
    private String medicalConditions;

    /**
     * Name of the emergency contact person.
     * Must not be empty and must be within size constraints.
     * Should not contain any special characters.
     */
    @NotBlank(message = "Contact name must not be empty")
    @Size(min = 1, max = 150, message = "Contact name must be between 1 and 150 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Contact name must not contain special characters")
    private String contactName;

    /**
     * Phone number of the emergency contact person.
     * Must not be empty and must be within size constraints.
     * Should not contain any special characters.
     */
    @NotBlank(message = "Contact phone must not be empty")
    @Size(min = 1, max = 150, message = "Contact phone must be between 1 and 150 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Contact phone must not contain special characters")
    private String contactPhone;
}
