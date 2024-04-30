package com.sitas.checkin.domain.model.user;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Integer personId;

    @Column(name = "id_identification_type", nullable = false)
    private Integer identificationTypeId;

    @Column(name = "identification_number", nullable = false, unique = true)
    private String identificationNumber;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "genre", nullable = false)
    private char genre;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "province", nullable = false)
    private String province;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "residence", nullable = false)
    private String residence;

    @Column(name = "mail", nullable = false, unique = true)
    private String mail;

    @Column(name = "access_key", nullable = false)
    private String accessKey;
}
