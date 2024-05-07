package com.sitas.checkin.domain.model.user;

import jakarta.persistence.*;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

/**
 * Represents medical information related to a person.
 * This class is mapped to the "MEDICAL_INFO" table in the database.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "MEDICAL_INFO")
public class MedicalInfo {
    /**
     * Unique identifier for medical information.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEDICAL_INFO_ID")
    private Integer medicalInfoId;

    /**
     * Identifier of the person associated with this medical information.
     */
    @JoinColumn(name = "PERSON", referencedColumnName = "PERSON_ID")
    @Column (name = "PERSON_ID")
    private Integer personId;

    /**
     * Medical conditions of the person.
     * Must not be empty and must be within size constraints.
     * Should not contain any special characters.
     */
    @Column(name = "MEDICAL_CONDITIONS")
    @Size(min = 1, max = 150)
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Medical Conditions must not contain special characters")
    private String medicalConditions;

    /**
     * Name of the emergency contact person.
     * Must not be empty and must be within size constraints.
     * Should not contain any special characters.
     */
    @Column(name = "EMERGENCY_CONTACT_NAME")
    @Size(min = 1, max = 150)
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Contact name must not contain special characters")
    private String contactName;

    /**
     * Phone number of the emergency contact person.
     * Must not be empty and must be within size constraints.
     * Should not contain any special characters.
     */
    @Column(name = "EMERGENCY_CONTACT_PHONE")
    @Size(min = 1, max = 150)
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Contact phone must not contain special characters")
    private String contactPhone;
}
