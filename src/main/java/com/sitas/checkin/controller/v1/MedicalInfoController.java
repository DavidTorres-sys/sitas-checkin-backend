package com.sitas.checkin.controller.v1;


import com.sitas.checkin.domain.model.user.MedicalInfo;
import com.sitas.checkin.services.medicalinfo.service.MedicalInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/medical-info")
public class MedicalInfoController {
    @Autowired
    private MedicalInfoServiceImpl service;
    @PostMapping("/add-medical-info")
    public MedicalInfo addMedicalInfo(@RequestBody MedicalInfo medicalInfo){
        return service.saveMedicalInfo(medicalInfo);
    }
}
