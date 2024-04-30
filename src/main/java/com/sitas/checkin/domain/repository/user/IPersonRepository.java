package com.sitas.checkin.domain.repository.user;

import com.sitas.checkin.domain.model.user.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPersonRepository extends JpaRepository<Person,Integer> {
    Optional<Person> findByLastName(String lastName);
}
