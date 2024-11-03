package com.sitas.checkin.persistance.repository.user;

import com.sitas.checkin.domain.model.user.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * Interface for accessing and managing Passenger entities in the database.
 * Extends JpaRepository interface to inherit CRUD methods.
 */
public interface IPassengerRepository extends JpaRepository<Passenger,Integer> {
    /**
     * Retrieves a passenger by their person ID.
     *
     * @param personId The ID of the person associated with the passenger.
     * @return An Optional containing the passenger if found, otherwise empty.
     */
    public Optional<Passenger> findByPersonId(Integer personId);
}
