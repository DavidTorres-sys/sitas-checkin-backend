package com.sitas.checkin.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@Table(name = "MEDICAL_INFO")
public class MedicalInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int medicalInfoId;


    @JoinColumn(name = "PERSON", referencedColumnName = "PERSON_ID")
    @Column (name = "PERSON_ID")
    private Integer personId;

    @Column (name = "MEDICAL_CONDITIONS")
    private String medicalConditions;

    @Column (name = "EMERGENCY_CONTACT_NAME")
    private String contactName;

    @Column (name = "EMERGENCY_CONTACT_PHONE")
    private String contactPhone;
}
