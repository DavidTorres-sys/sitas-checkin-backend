package com.sitas.checkin.domain.repository.airline;

import com.sitas.checkin.domain.model.airline.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interface for accessing and managing Flight entities in the database.
 * Extends JpaRepository interface to inherit CRUD methods.
 */
public interface IFlightRepository  extends JpaRepository<Flight,Integer> {
    Optional<Flight> findByFlightNumber(String flightNumber);
}
