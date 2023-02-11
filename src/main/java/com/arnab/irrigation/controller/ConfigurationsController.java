package com.arnab.irrigation.controller;

import com.arnab.irrigation.domain.PlotConfiguration;
import com.arnab.irrigation.dto.ConfigurePlotDTO;
import com.arnab.irrigation.service.PlotConfigurationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/configuration")
public class ConfigurationsController {
    
    private final PlotConfigurationService plotConfigurationService;
    private final ModelMapper modelMapper;

    @GetMapping("")
    public ResponseEntity<List<PlotConfiguration>> getAllConfigurations(){
        List<PlotConfiguration> configs = plotConfigurationService.getAllConfiguration();

        return new ResponseEntity<>(configs,HttpStatus.OK);
    }

    @GetMapping("/{configurationId}")
    public ResponseEntity<PlotConfiguration> getConfigurationById(@PathVariable String configurationId){
        PlotConfiguration config = plotConfigurationService.getConfigurationById(configurationId);


        return new ResponseEntity<>(config,HttpStatus.OK);
    }
    
}
