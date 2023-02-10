package com.arnab.irrigation.service;

import com.arnab.irrigation.dto.EmailDTO;

/**
 *
 * @author Arnab Bhattacharyya
 */
public interface AlertService {

    void sendMailAlert(String deviceName);
}
