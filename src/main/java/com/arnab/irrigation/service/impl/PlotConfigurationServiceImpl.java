/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arnab.irrigation.service.impl;

import com.arnab.irrigation.domain.PlotConfiguration;
import com.arnab.irrigation.domain.Plot;
import com.arnab.irrigation.dto.ConfigurePlotDTO;
import com.arnab.irrigation.exception.BadRequestException;

import java.util.*;

import com.arnab.irrigation.repository.PlotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.arnab.irrigation.service.PlotConfigurationService;
import com.arnab.irrigation.repository.PlotConfigurationRepository;

/**
 *
 * @author Arnab Bhattacharyya
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PlotConfigurationServiceImpl implements PlotConfigurationService {

    private final PlotConfigurationRepository repository;

    private final PlotRepository plotRepository;
    private final ModelMapper modelMapper;
    
    
    @Override
    public PlotConfiguration configurePlot(ConfigurePlotDTO model,Plot plot) {

        if(plot==null){
            throw new BadRequestException("Plot not found");
        } 
        PlotConfiguration device = modelMapper.map(model, PlotConfiguration.class);
        
        if(model.getTimeSlot().getTime() < new Date().getTime()){   
            throw new BadRequestException("Invalid date, time slot cannot be in the past");   
        }
        device.setNextTimeSlot(model.getTimeSlot());
        device.setCreatedOn(new Date());
        
        PlotConfiguration configuration = repository.save(device);
        Optional<Plot> entityRef = plotRepository.findByCode(plot.getCode());
        if(entityRef.isPresent()){
            Plot entity = entityRef.get();

            if(entity.getPlotConfigurations()!=null)
                entity.getPlotConfigurations().add(configuration);
            else {
                List<PlotConfiguration> plotConfigurations = new ArrayList<>();
                plotConfigurations.add(configuration);
                entity.setPlotConfigurations(plotConfigurations);
            }

            plotRepository.save(entity);
            return device;
        }else{
            return null;
        }

    }

    @Override
    public List<PlotConfiguration> getConfigurationSchedules() {
        
        return repository.findByNextTimeSlotEquals(new Date());
    }

    @Override
    public List<PlotConfiguration> getAllConfiguration() {

        return repository.findAll();
    }

    @Override
    public PlotConfiguration getConfigurationById(String id) {
        Optional<PlotConfiguration> document = repository.findById(new ObjectId(id));
        return document.get();
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
