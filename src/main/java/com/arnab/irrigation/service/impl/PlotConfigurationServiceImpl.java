/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arnab.irrigation.service.impl;

import com.arnab.irrigation.domain.PlotConfiguration;
import com.arnab.irrigation.domain.Plot;
import com.arnab.irrigation.dto.ConfigurePlotDTO;
import com.arnab.irrigation.exception.BadRequestException;

import java.util.Date;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import com.arnab.irrigation.service.PlotConfigurationService;
import com.arnab.irrigation.repository.PlotConfigurationRepository;
import java.util.Calendar;

/**
 *
 * @author Arnab Bhattacharyya
 */
@Service
@RequiredArgsConstructor
public class PlotConfigurationServiceImpl implements PlotConfigurationService {

    private final PlotConfigurationRepository repository;
    private final ModelMapper modelMapper;
    
    
    @Override
    public PlotConfiguration configurePlot(ConfigurePlotDTO model,Plot Plot) {
                
        if(Plot==null){
            throw new BadRequestException("Plot not found");
        } 
        PlotConfiguration device = modelMapper.map(model, PlotConfiguration.class);
        
        if(model.getTimeSlot().getTime() < new Date().getTime()){   
            throw new BadRequestException("Invalid date, time slot cannot be in the past");   
        }
        
        device.setPlot(Plot);
        device.setNextTimeSlot(model.getTimeSlot());
        device.setCreatedOn(new Date());
        
        repository.save(device);   
        return device;  
    }

    @Override
    public List<PlotConfiguration> getConfigurationSchedules() {
        
        return repository.findByNextTimeSlotEquals(new Date());
    }

    @Override
    public PlotConfiguration updateNextTimeSlot(PlotConfiguration configuration) {
        
        var calendar = Calendar.getInstance();
        calendar.setTime(configuration.getNextTimeSlot());
        calendar.add(Calendar.HOUR_OF_DAY, configuration.getIntervalInDays());
        var timeSlot =  calendar.getTime();    
        configuration.setNextTimeSlot(timeSlot);
        configuration.setModifiedOn(new Date());
        repository.save(configuration);
        return configuration;
    }
    
}
