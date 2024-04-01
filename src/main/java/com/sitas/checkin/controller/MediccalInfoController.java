package com.sitas.checkin.controller;


import com.sitas.checkin.domain.entity.MedicalInfoEntity;
import com.sitas.checkin.services.MedicalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MediccalInfoController {
    @Autowired
    private MedicalInfoService service;
    @PostMapping("/addMedicalInfo")
    public MedicalInfoEntity addMedicalInfo(@RequestBody MedicalInfoEntity medicalInfo){
        return service.saveMedicalInfo(medicalInfo);
    }
}
