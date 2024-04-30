package com.sitas.checkin.domain.repository.user;

import com.sitas.checkin.domain.model.user.MedicalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for accessing and managing MedicalInfo entities in the database.
 * Extends JpaRepository interface to inherit CRUD methods.
 */
public interface IMedicalInfoRepository extends JpaRepository<MedicalInfo,Integer> { }
