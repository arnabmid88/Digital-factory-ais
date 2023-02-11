package com.arnab.irrigation.service;

import com.arnab.irrigation.domain.Plot;
import com.arnab.irrigation.dto.ConfigurePlotDTO;
import com.arnab.irrigation.dto.PlotDTO;
import java.util.List;

/**
 *
 * @author Arnab Bhattacharyya
 */
public interface PlotService {
    
    /**
     * add a new land
     *
     * @param model LandDTO must not be {@literal null}.
     * @return never {@literal null}.
     */
    Plot addPlot(PlotDTO model);
    

    /**
     * edit an existing land
     *
     * @param model LandDTO must not be {@literal null}.
     * @param id String.
     * @return never {@literal null}.
     */
    Plot editPlot(PlotDTO model,String id);

    Plot configurePlot(ConfigurePlotDTO model,String id);
    /**
     * Returns specified category as well as all descendents
     * @param id parent category id
     * @return specified category and descendents
     */
    Plot getPlotById(String id);
    List<Plot> getAllPlots();
    
}
