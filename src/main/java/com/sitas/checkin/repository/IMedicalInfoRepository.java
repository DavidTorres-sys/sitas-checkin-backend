package com.sitas.checkin.repository;

import com.sitas.checkin.domain.entity.LuggageInfoEntity;
import com.sitas.checkin.domain.entity.MedicalInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedicalInfoRepository extends JpaRepository<MedicalInfoEntity,Integer> {
}
