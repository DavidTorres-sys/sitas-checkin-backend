package com.sitas.checkin.services.luggageinfo.service;

import com.sitas.checkin.domain.model.user.LuggageInfo;
import org.springframework.http.ResponseEntity;

/**
 * Service interface for managing luggage info.
 */
public interface ILuggageInfoService {

    /**
     * Saves luggage information.
     *
     * @param luggageInfo The luggage information to be saved.
     * @return ResponseEntity with the saved luggage information.
     */
    public ResponseEntity<LuggageInfo> saveLuggageInfo(LuggageInfo luggageInfo);

    /**
     * Retrieves luggage information by ID.
     *
     * @param luggageInfoId The ID of the luggage information to retrieve.
     * @return ResponseEntity with luggage information if found, else returns a 404 Not Found response.
     */
    public ResponseEntity<LuggageInfo> getLuggageInfo(Integer luggageInfoId);
}
