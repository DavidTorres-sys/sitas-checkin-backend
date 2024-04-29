package com.sitas.checkin.services.medicalinfo.service;

import com.sitas.checkin.domain.dto.MedicalInfoDTO;
import com.sitas.checkin.domain.jpa.model.user.MedicalInfo;
import com.sitas.checkin.domain.jpa.repository.user.IMedicalInfoRepository;
import com.sitas.checkin.domain.jpa.mapper.user.IMedicalInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MedicalInfoService implements IMedicalInfoService {

    private final IMedicalInfoRepository repository;
    private final IMedicalInfoMapper mapper;

    @Autowired
    public MedicalInfoService(IMedicalInfoRepository repository, @Qualifier("medicalInfoMapper") IMedicalInfoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public MedicalInfoDTO saveMedicalInfo(MedicalInfoDTO medicalInfoDTO){
        // Convert DTO to entity using the mapper before saving
        MedicalInfo medicalInfo = mapper.medicalInfoDTOToMedicalInfo(medicalInfoDTO);
        // Delegate the saving operation to the repository
        MedicalInfo savedMedicalInfo = repository.save(medicalInfo);
        // Convert the saved entity back to DTO and return
        return mapper.medicalInfoToMedicalInfoDTO(savedMedicalInfo);
    }
}