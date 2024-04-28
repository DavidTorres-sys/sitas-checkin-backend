package com.sitas.checkin.domain.mapper.user;

import com.sitas.checkin.domain.dto.MedicalInfoDTO;
import com.sitas.checkin.domain.jpa.model.user.MedicalInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for mapping between the MedicalInfo entity and its corresponding DTO.
 * This interface defines methods to map MedicalInfo entities to MedicalInfoDTOs and vice versa.
 */
@Mapper
public interface IMedicalInfoMapper {

    /**
     * Singleton instance of the IMedicalInfoMapper interface.
     */
    IMedicalInfoMapper INSTANCE = Mappers.getMapper(IMedicalInfoMapper.class);

    /**
     * Maps a MedicalInfo entity to a MedicalInfoDTO.
     *
     * @param medicalInfo The MedicalInfo entity to be mapped.
     * @return The mapped MedicalInfoDTO.
     */
    MedicalInfoDTO medicalInfoToMedicalInfoDTO(MedicalInfo medicalInfo);

    /**
     * Maps a MedicalInfoDTO to a MedicalInfo entity.
     *
     * @param medicalInfoDTO The MedicalInfoDTO to be mapped.
     * @return The mapped MedicalInfo entity.
     */
    MedicalInfo medicalInfoDTOToMedicalInfo(MedicalInfoDTO medicalInfoDTO);
}
