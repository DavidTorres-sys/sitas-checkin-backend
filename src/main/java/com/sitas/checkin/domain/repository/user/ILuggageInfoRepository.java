package com.sitas.checkin.domain.repository.user;
import com.sitas.checkin.domain.model.user.LuggageInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for accessing and managing LuggageInfo entities in the database.
 * Extends JpaRepository interface to inherit CRUD methods.
 */
public interface ILuggageInfoRepository extends JpaRepository<LuggageInfo,Integer> { }
