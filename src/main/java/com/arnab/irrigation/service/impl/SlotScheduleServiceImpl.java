package com.arnab.irrigation.service.impl;

import com.arnab.irrigation.domain.SlotSchedule;
import com.arnab.irrigation.domain.PlotConfiguration;
import com.arnab.irrigation.domain.enums.DeviceStatus;
import com.arnab.irrigation.repository.SlotScheduleRepository;
import com.arnab.irrigation.service.AlertService;
import com.arnab.irrigation.service.DeviceControllerService;
import com.arnab.irrigation.service.SlotScheduleService;
import com.arnab.irrigation.service.PlotConfigurationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *
 * @author Arnab Bhattacharyya
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SlotScheduleServiceImpl implements SlotScheduleService {
     
    private final SlotScheduleRepository repository;
    private final PlotConfigurationService plotConfigurationService;
    private final AlertService  alertService;
    private final DeviceControllerService deviceControllerService;
    private final ModelMapper modelMapper;
    
    @Value("${number.of.sensor.retries}")
    private int numberOfSensorRetries;
    
    @Override
    public SlotSchedule createSchedule(PlotConfiguration config) {
        
        SlotSchedule schedule = new SlotSchedule();
        schedule.setCreatedOn(new Date());
        schedule.setAmountOfWater(config.getAmountOfWater());
        schedule.setDurationInMinutes(config.getDurationInMinutes());
        schedule.setPlotConfiguration(config);
        repository.save(schedule);
        return schedule;
    }

    @Override
    public SlotSchedule executeSchedule(SlotSchedule schedule) {
        
        var status = deviceControllerService.sendSlotSchedule(schedule);
        var retries = 0;
        while(status == DeviceStatus.NOTAVAILABLE && retries < numberOfSensorRetries){
            status = deviceControllerService.sendSlotSchedule(schedule);
            retries++;
        }
        
        //// implement alert after exceeding retry and status is still not available
        if(status == DeviceStatus.NOTAVAILABLE){
            alertService.sendMailAlert(schedule.getPlotConfiguration().getDeviceName());
        }
        schedule.setStatus(status);
        repository.save(schedule);
        return schedule;
    }
    
}
