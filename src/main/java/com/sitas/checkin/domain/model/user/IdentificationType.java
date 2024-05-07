package com.sitas.checkin.domain.model.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@Table(name = "IdentificationType")
public class IdentificationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identification_type_id")
    private Integer identificationTypeId;

    @Column(name = "identification_type", nullable = false)
    private String identificationType;
}
