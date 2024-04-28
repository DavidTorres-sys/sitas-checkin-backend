package com.sitas.checkin.domain.mapper.user;

import com.sitas.checkin.domain.dto.LuggageInfoDTO;
import com.sitas.checkin.domain.jpa.model.user.LuggageInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for mapping between the LuggageInfo entity and its corresponding DTO.
 * This interface defines methods to map LuggageInfo entities to LuggageInfoDTOs and vice versa.
 */
@Mapper
public interface ILuggageInfoMapper {
    /**
     * Singleton instance of the ILuggageInfoMapper interface.
     */
    ILuggageInfoMapper INSTANCE = Mappers.getMapper(ILuggageInfoMapper.class);

    /**
     * Maps a LuggageInfo entity to a LuggageInfoDTO.
     *
     * @param luggageInfo The LuggageInfo entity to be mapped.
     * @return The mapped LuggageInfoDTO.
     */
    LuggageInfoDTO luggageInfoToLuggageInfoDTO(LuggageInfo luggageInfo);

    /**
     * Maps a LuggageInfoDTO to a LuggageInfo entity.
     *
     * @param luggageInfoDTO The LuggageInfoDTO to be mapped.
     * @return The mapped LuggageInfo entity.
     */
    LuggageInfo luggageInfoDTOToLuggageInfo(LuggageInfoDTO luggageInfoDTO);
}