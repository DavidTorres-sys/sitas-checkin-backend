package com.sitas.checkin.domain.repository.user;

import com.sitas.checkin.domain.model.user.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * Interface for accessing and managing Passenger entities in the database.
 * Extends JpaRepository interface to inherit CRUD methods.
 */
public interface IPassengerRepository extends JpaRepository<Passenger,Integer> {
    public Optional<Passenger> findByPersonId(Integer personId);
}
