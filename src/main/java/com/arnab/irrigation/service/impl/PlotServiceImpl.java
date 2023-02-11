package com.arnab.irrigation.service.impl;

import com.arnab.irrigation.domain.Plot;
import com.arnab.irrigation.dto.ConfigurePlotDTO;
import com.arnab.irrigation.dto.PlotDTO;
import com.arnab.irrigation.exception.BadRequestException;
import com.arnab.irrigation.exception.ResourceNotFoundException;
import com.arnab.irrigation.repository.PlotRepository;
import com.arnab.irrigation.service.PlotConfigurationService;
import com.arnab.irrigation.service.PlotService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Arnab Bhattacharyya
 */
@Service
@RequiredArgsConstructor
public class PlotServiceImpl implements PlotService {
    
    private final PlotRepository repository;
    private final PlotConfigurationService  iotDeviceService;
    private final ModelMapper modelMapper;

    @Override
    public Plot addPlot(PlotDTO model) {
        
        ///check if Plot code already exists         
        Optional<Plot> Plot = repository.findByCode(model.getCode());
        
        if(Plot.isPresent()){
            throw new BadRequestException("Plot code already exists");
        }
        
        Plot entity = modelMapper.map(model, Plot.class);
        entity.setCreatedOn(new Date());
        repository.save(entity);
        return entity;  
    }

    @Override
    public Plot editPlot(PlotDTO model, String id) {
        Optional<Plot> Plot = repository.findById(new ObjectId(id));
        
        if(!Plot.isPresent()){
            throw new BadRequestException("Plot not found");
        }
        Plot entity = Plot.get();
        entity.setCode(model.getCode());
        entity.setArea(model.getArea());
        entity.setPlotType(model.getPlotType());
        entity.setModifiedOn(new Date());
        repository.save(entity);
        return entity;  
    }

    @Override
    public Plot configurePlot(ConfigurePlotDTO model,String id) {
        Optional<Plot> Plot = repository.findById(new ObjectId(id));
        
        if(Plot.isEmpty()){
            throw new BadRequestException("Plot not found");
        }
                
        iotDeviceService.configurePlot(model, Plot.get());     
        return Plot.get();   
    }

    @Override
    public Plot getPlotById(String id) {
        Optional<Plot> Plot = repository.findById(new ObjectId(id));
        if(Plot.isEmpty()){
            throw new ResourceNotFoundException("Plot not found");
        }  
        return Plot.get();
    }

    @Override
    public List<Plot> getAllPlots() {
        return repository.findAll();
    }
    
}
