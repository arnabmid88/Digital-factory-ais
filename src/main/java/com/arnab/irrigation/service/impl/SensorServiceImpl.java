package com.arnab.irrigation.service.impl;

import com.arnab.irrigation.domain.SlotSchedule;
import com.arnab.irrigation.domain.enums.DeviceStatus;
import com.arnab.irrigation.service.DeviceControllerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author Arnab Bhattacharyya
 */
@Service
@RequiredArgsConstructor
public class SensorServiceImpl implements DeviceControllerService {

    @Override
    public DeviceStatus sendSlotSchedule(SlotSchedule slotSchedule) {
        DeviceStatus[] expectedStatus = new DeviceStatus[]{DeviceStatus.FAILED, DeviceStatus.SUCCESSFUL, DeviceStatus.NOTAVAILABLE};
                
        ///get random integer between 0 and 1;
        int rand = (int)(Math.random() * 3);
        DeviceStatus status = expectedStatus[rand];
        ///connect to the irrigation sensor service to send irrigation schedule
        return status;
    }
    
}
