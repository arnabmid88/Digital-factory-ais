/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arnab.irrigation.dataprovider;

import com.arnab.irrigation.domain.Plot;
import com.arnab.irrigation.dto.ConfigurePlotDTO;
import com.arnab.irrigation.dto.PlotDTO;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Arnab Bhattacharyya
 */
public class PlotDataProvider {
    
    public static final String Plot_CODE = "2722782392889";
    public static final float Plot_AREA = 23348;
    public static final PlotDTO getPlotDTO(String code,String PlotType, float area){
        
        PlotDTO Plot = new PlotDTO();
        Plot.setCode(code);
        Plot.setArea(area);
        return Plot;
    }
    
    public static final PlotDTO getDefaultPlotDTO(){
        
        PlotDTO Plot = new PlotDTO();
        Plot.setCode(Plot_CODE);
        Plot.setArea(Plot_AREA);
        return Plot;
    }
    
    public static final ConfigurePlotDTO getDefaultConfigureDTO(){
        
        var calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, 2);
        var timeSlot =  calendar.getTime();
        ConfigurePlotDTO configure = new ConfigurePlotDTO();
        configure.setDeviceName("device101");
        configure.setAmountOfWater(3000);
        configure.setDurationInMinutes(3600);
        configure.setTimeSlot(timeSlot);
        configure.setIntervalInDays(3);
        return configure;
    }
    
     public static final Plot getPlotFromPlotDTO(PlotDTO dto,String id){
        Plot Plot = new Plot();
        Plot.setCode(dto.getCode());
        Plot.setArea(dto.getArea());
        Plot.setId(id);
        return Plot;
    }
    
}
