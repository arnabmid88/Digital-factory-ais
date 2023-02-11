package com.arnab.irrigation.controller;

import com.arnab.irrigation.domain.Plot;
import com.arnab.irrigation.dto.ConfigurePlotDTO;
import com.arnab.irrigation.dto.PlotDTO;
import com.arnab.irrigation.service.PlotService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/plot")
public class PlotController {
    
    private final PlotService PlotService;
    private final ModelMapper modelMapper;

    
    @PostMapping("")
    public ResponseEntity<Plot> createPlot(@Valid @RequestBody PlotDTO model){
        Plot drone = PlotService.addPlot(model);
        return new ResponseEntity<>(drone,HttpStatus.OK); 
    }
    
    @PutMapping("{PlotId}")
    public ResponseEntity<Plot> updatePlot(@Valid @RequestBody PlotDTO model,@PathVariable String PlotId){
        Plot drone = PlotService.editPlot(model,PlotId);
        return new ResponseEntity<>(drone,HttpStatus.OK); 
    }
    
    
    @PostMapping("{PlotId}/configure")
    public ResponseEntity<PlotDTO> createPlotConfiguration(@Valid @RequestBody ConfigurePlotDTO model,@PathVariable String PlotId){
        Plot Plot = PlotService.configurePlot(model,PlotId);
        var PlotDTO = modelMapper.map(Plot, PlotDTO.class);
        return new ResponseEntity<>(PlotDTO,HttpStatus.OK); 
    }
    
    @GetMapping("")
    public ResponseEntity<List<PlotDTO>> getAllPlots(){
        List<Plot> Plots = PlotService.getAllPlots();
        
        List<PlotDTO> dto = Plots.stream().map(m-> modelMapper.map(m, PlotDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    
}
