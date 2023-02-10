/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arnab.irrigation.service;

import com.arnab.irrigation.domain.PlotConfiguration;
import com.arnab.irrigation.domain.Plot;
import com.arnab.irrigation.dto.ConfigurePlotDTO;
import com.arnab.irrigation.dto.PlotDTO;
import java.util.List;

/**
 *
 * @author Arnab Bhattacharyya
 */
public interface PlotConfigurationService {
    
    /**
     * add a new Plot
     *
     * @param model PlotDTO must not be {@literal null}.
     * @return never {@literal null}.
     */
    PlotConfiguration configurePlot(ConfigurePlotDTO model,Plot Plot);
    
    List<PlotConfiguration> getConfigurationSchedules();
    
    PlotConfiguration updateNextTimeSlot(PlotConfiguration configuration);
}
