package com.sitas.checkin.domain.repository.user;

import com.sitas.checkin.domain.model.user.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPersonRepository extends JpaRepository<Person,Integer> {
    /**
     * Retrieves a person by their last name.
     *
     * @param lastName The last name of the person to retrieve.
     * @return An Optional containing the person if found, otherwise empty.
     */
    Optional<Person> findByLastName(String lastName);
}
