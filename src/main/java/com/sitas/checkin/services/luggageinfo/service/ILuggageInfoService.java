package com.sitas.checkin.services.luggageinfo.service;

import com.sitas.checkin.domain.model.user.LuggageInfo;
import jakarta.mail.Message;
import org.springframework.http.ResponseEntity;

import java.util.List;

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

    /**
     * Delete luggage information by ID.
     *
     * @param luggageInfoId The ID of the luggage information to retrieve.
     * @return String with a message if delete correctly, else returns a 404 Not Found response.
     */
    public ResponseEntity<String> deleteLuggageInfo(Integer luggageInfoId);

    /**
     * Retrieves all luggage information.
     *
     * @return ResponseEntity containing a list of all luggage information.
     */
    public ResponseEntity<List<LuggageInfo>> getAllLuggageInfo();

    /**
     * Updates existing luggage information.
     *
     * @param luggageInfoId The ID of the luggage information to update.
     * @param luggageInfo   The updated luggage information.
     * @return ResponseEntity containing the updated luggage information,
     *         or a 404 Not Found response if the luggage information does not exist.
     */
    public ResponseEntity<LuggageInfo> putLuggageInfo(Integer luggageInfoId, LuggageInfo luggageInfo);
}
