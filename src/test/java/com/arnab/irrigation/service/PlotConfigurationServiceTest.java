package com.arnab.irrigation.service;

import com.arnab.irrigation.dataprovider.PlotDataProvider;
import com.arnab.irrigation.domain.Plot;
import com.arnab.irrigation.repository.PlotConfigurationRepository;
import com.arnab.irrigation.service.impl.PlotConfigurationServiceImpl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;

import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Arnab Bhattacharyya
 */

@ExtendWith(MockitoExtension.class)
public class PlotConfigurationServiceTest {
     
    @Mock
    private PlotConfigurationRepository repository;
    
    
    @InjectMocks
    private PlotConfigurationServiceImpl  PlotConfigurationService;
    
    @Spy
    private ModelMapper modelMapper;
    
     @Test
    public void configurePlot_idExists_expectReturnPlotConfigure(){
        
        var configureDto = PlotDataProvider.getDefaultConfigureDTO();
        Long id = (long)3;
        Plot Plot = new Plot();                      
        this.PlotConfigurationService.configurePlot(configureDto,Plot);
        verify(repository).save(any());
    }
}
