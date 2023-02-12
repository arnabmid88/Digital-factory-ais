package com.arnab.irrigation.service;

import com.arnab.irrigation.dataprovider.PlotDataProvider;
import com.arnab.irrigation.domain.PlotConfiguration;
import com.arnab.irrigation.domain.Plot;
import com.arnab.irrigation.dto.PlotDTO;
import com.arnab.irrigation.exception.BadRequestException;
import com.arnab.irrigation.exception.ResourceNotFoundException;
import com.arnab.irrigation.repository.PlotRepository;
import com.arnab.irrigation.service.impl.PlotServiceImpl;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Arnab Bhattacharyya
 */
@ExtendWith(MockitoExtension.class)
public class PlotServiceTest {
    
    @Mock
    private PlotRepository repository;

    @Mock
    private PlotConfigurationService  PlotConfigurationService;
    
    @InjectMocks
    private PlotServiceImpl  PlotService;
    
    @Spy
    private ModelMapper modelMapper;
    
    @Test
    public void addPlot_codeAlreadyExists_expectException(){
        
        PlotDTO PlotDto = PlotDataProvider.getDefaultPlotDTO();
        
        when(repository.findByCode(PlotDto.getCode()))
                .thenReturn(Optional.of(new Plot()));
        
        assertThatThrownBy(()-> this.PlotService.addPlot(PlotDto))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("Plot code already exists");
        
        verify(repository).findByCode(any());
    }
    
    @Test
    public void addPlot_codeNotExists_expectReturnNewPlot(){
        
        PlotDTO PlotDto = PlotDataProvider.getDefaultPlotDTO();
        String id = new ObjectId().toHexString();
        Plot Plot = PlotDataProvider.getPlotFromPlotDTO(PlotDto,id);
        
        when(repository.findByCode(PlotDto.getCode()))
                .thenReturn(Optional.empty());
        
        when(repository.save(any()))
                .thenReturn(Plot);
        
        this.PlotService.addPlot(PlotDto);
      
        verify(repository).findByCode(any());
        verify(repository).save(any());
    }
    
    
    @Test
    public void editPlot_IdNotFound_expectException(){
        
        PlotDTO PlotDto = PlotDataProvider.getDefaultPlotDTO();
        String id = new ObjectId().toHexString();
        
        when(repository.findById(new ObjectId(id)))
                .thenReturn(Optional.empty());
        
        assertThatThrownBy(()-> this.PlotService.editPlot(PlotDto,id))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("Plot not found");
        
        verify(repository).findById((ObjectId) any());
    }
    
    @Test
    public void editPlot_idExists_expectReturnUpdatedPlot(){
        
        PlotDTO PlotDto = PlotDataProvider.getDefaultPlotDTO();
        String id = "63e7d690793939669357501c";
        Plot Plot = PlotDataProvider.getPlotFromPlotDTO(PlotDto,id);
        
        when(repository.findById(new ObjectId(id)))
                .thenReturn(Optional.of(Plot));
        
        when(repository.save(any()))
                .thenReturn(Plot);
        
        this.PlotService.editPlot(PlotDto,id);
      
        verify(repository).findById((ObjectId) any());
        verify(repository).save(any());
    }
    
    @Test
    public void configurePlot_IdNotFound_expectException(){
        
        var configureDto = PlotDataProvider.getDefaultConfigureDTO();
        String id = new ObjectId().toHexString();
        
        when(repository.findById(new ObjectId(id)))
                .thenReturn(Optional.empty());
       
        assertThatThrownBy(()-> this.PlotService.configurePlot(configureDto,id))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("Plot not found");
        
        verify(repository).findById((ObjectId) any());
    }
    
    @Test
    public void configurePlot_idExists_expectReturnPlotConfigure(){
        
        var configureDto = PlotDataProvider.getDefaultConfigureDTO();
        String id = "63e7d690793939669357501c";
        Plot Plot = new Plot();        
        
        when(repository.findById(new ObjectId(id)))
                .thenReturn(Optional.of(Plot));
                
        when(PlotConfigurationService.configurePlot(configureDto,Plot))
                .thenReturn(new PlotConfiguration());
               
        this.PlotService.configurePlot(configureDto,id);
      
        verify(repository).findById((ObjectId) any());
        verify(PlotConfigurationService).configurePlot(any(),any());
    }
    
     @Test
    public void getPlot_IdNotFound_expectException(){
        
        String id = "63e87cf989e8c928a7359396";
        when(repository.findById(new ObjectId(id)))
                .thenReturn(Optional.empty());
       
        assertThatThrownBy(()-> this.PlotService.getPlotById(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Plot not found");
        
        verify(repository).findById((ObjectId) any());
    }
    
    @Test
    public void getPlot_IdExist_expectReturnPlot(){
        
        String id = "63e7d690793939669357501c";
        Plot Plot = new Plot();  
        when(repository.findById(new ObjectId(id)))
                .thenReturn(Optional.of(Plot));
       var retPlot = this.PlotService.getPlotById(id);
       assertEquals(Plot,retPlot);
       verify(repository).findById((ObjectId) any());
    }
    
    @Before
    public void setup() throws Exception {    
        MockitoAnnotations.initMocks(this);
    }
    
    @After
    public void tearDown() throws Exception {
    }
    
}
