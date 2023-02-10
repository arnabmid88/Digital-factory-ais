package com.arnab.irrigation.service;

import com.arnab.irrigation.domain.SlotSchedule;
import com.arnab.irrigation.domain.enums.DeviceStatus;

/**
 *
 * @author Arnab Bhattacharyya
 */
public interface DeviceControllerService {
    
    DeviceStatus sendSlotSchedule(SlotSchedule SlotSchedule);
    
}
