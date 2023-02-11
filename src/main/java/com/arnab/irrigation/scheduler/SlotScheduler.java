package com.arnab.irrigation.scheduler;

import com.arnab.irrigation.service.PlotConfigurationService;
import com.arnab.irrigation.service.SlotScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class SlotScheduler  {
    
    private final PlotConfigurationService plotConfigurationService;
    private final SlotScheduleService slotScheduleService;
    
    @Scheduled(fixedRateString = "${schedule.interval}")
    public void createScheduleFromPlotConfig(){
        log.info("Running ");
        var landConfigs = plotConfigurationService.getConfigurationSchedules();
        log.info("Number of Schedules "+ landConfigs.size());
        
        for(var landConfig: landConfigs ){
            log.info("Creating schedule for device id "+ landConfig.getDeviceName());
            plotConfigurationService.updateNextTimeSlot(landConfig);
            
            var schedule = slotScheduleService.createSchedule(landConfig);
            slotScheduleService.executeSchedule(schedule);
        }
    }
    
}
