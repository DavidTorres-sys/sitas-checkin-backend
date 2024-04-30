package com.sitas.checkin.domain.repository.user;


import com.sitas.checkin.domain.model.user.BoardingPass;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for accessing and managing BoardingPass entities in the database.
 * Extends JpaRepository interface to inherit CRUD methods.
 */
public interface IBoardingPassRepository extends JpaRepository<BoardingPass,Integer> { }
