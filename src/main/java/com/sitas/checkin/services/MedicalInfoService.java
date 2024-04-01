package com.sitas.checkin.services;

import com.sitas.checkin.domain.entity.MedicalInfoEntity;
import com.sitas.checkin.repository.IMedicalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalInfoService {
    @Autowired
    private IMedicalInfoRepository repository;

    public MedicalInfoEntity saveMedicalInfo(MedicalInfoEntity medicalInfo){
        return repository.save(medicalInfo);
    }
}
