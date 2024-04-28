package com.sitas.checkin.services.medicalinfo.service;

import com.sitas.checkin.domain.jpa.model.user.MedicalInfo;
import com.sitas.checkin.domain.jpa.repository.user.IMedicalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalInfoService implements IMedicalInfoService {
    @Autowired
    private IMedicalInfoRepository repository;

    public MedicalInfo saveMedicalInfo(MedicalInfo medicalInfo){
        return repository.save(medicalInfo);
    }
}