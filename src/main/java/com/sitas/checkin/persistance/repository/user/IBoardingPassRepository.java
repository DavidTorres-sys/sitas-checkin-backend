package com.sitas.checkin.persistance.repository.user;


import com.sitas.checkin.domain.model.user.BoardingPass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interface for accessing and managing BoardingPass entities in the database.
 * Extends JpaRepository interface to inherit CRUD methods.
 */
public interface IBoardingPassRepository extends JpaRepository<BoardingPass,Integer> {
    /**
     * Retrieves a boarding pass by passenger.
     *
     * @param passengerId The passenger id associated with the boarding pass.
     * @return The boarding pass associated with the passenger.
     */
    public Optional<BoardingPass> findByPassengerPassengerId(Integer passengerId);


    public Optional<BoardingPass> findByPassengerPersonId(Integer personId);
}
