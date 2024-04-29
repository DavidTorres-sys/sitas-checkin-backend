package com.sitas.checkin.controller.v1;

import com.sitas.checkin.domain.dto.MedicalInfoDTO;
import com.sitas.checkin.services.medicalinfo.service.MedicalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/medical-info")
public class MedicalInfoController {
    @Autowired
    private MedicalInfoService service;
    @PostMapping("/add-medical-info")
    public MedicalInfoDTO addMedicalInfo(@RequestBody MedicalInfoDTO medicalInfo){
        return service.saveMedicalInfo(medicalInfo);
    }
}
