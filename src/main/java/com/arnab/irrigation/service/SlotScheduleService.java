package com.arnab.irrigation.service;

import com.arnab.irrigation.domain.SlotSchedule;
import com.arnab.irrigation.domain.PlotConfiguration;


/**
 *
 * @author Arnab Bhattacharyya
 */
public interface SlotScheduleService {
    
    SlotSchedule createSchedule(PlotConfiguration device);
    SlotSchedule executeSchedule(SlotSchedule schedule);
    
}
