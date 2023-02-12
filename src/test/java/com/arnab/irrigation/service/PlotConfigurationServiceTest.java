package com.arnab.irrigation.service;

import com.arnab.irrigation.dataprovider.PlotDataProvider;
import com.arnab.irrigation.domain.Plot;
import com.arnab.irrigation.dto.PlotDTO;
import com.arnab.irrigation.repository.PlotConfigurationRepository;
import com.arnab.irrigation.repository.PlotRepository;
import com.arnab.irrigation.service.impl.PlotConfigurationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.verify;

/**
 *
 * @author Arnab Bhattacharyya
 */

@ExtendWith(MockitoExtension.class)
public class PlotConfigurationServiceTest {
     
    @Mock
    private PlotConfigurationRepository repository;

    @Mock
    private PlotRepository plotRepository;
    
    @InjectMocks
    private PlotConfigurationServiceImpl  PlotConfigurationService;
    
    @Spy
    private ModelMapper modelMapper;
    
     @Test
    public void configurePlot_idExists_expectReturnPlotConfigure(){
        String existing_code = "73732825";
        var configureDto = PlotDataProvider.getDefaultConfigureDTO();
        PlotDTO plotDTO = PlotDataProvider.getDefaultPlotDTO();
        plotDTO.setCode(existing_code);
        Plot plot = modelMapper.map(plotDTO, Plot.class);
        this.PlotConfigurationService.configurePlot(configureDto,plot);
        verify(plotRepository).findByCode(existing_code);
    }
}
