package com.sitas.checkin.services.medicalinfo.service;

import com.sitas.checkin.domain.jpa.model.user.MedicalInfo;

public interface IMedicalInfoService {

    public MedicalInfo saveMedicalInfo(MedicalInfo medicalInfo);
}
