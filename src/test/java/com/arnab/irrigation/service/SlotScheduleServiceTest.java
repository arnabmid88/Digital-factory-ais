package com.arnab.irrigation.service;

import com.arnab.irrigation.dataprovider.PlotDataProvider;
import com.arnab.irrigation.domain.Plot;
import com.arnab.irrigation.domain.PlotConfiguration;
import com.arnab.irrigation.repository.SlotScheduleRepository;
import com.arnab.irrigation.service.impl.SlotScheduleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

/**
 *
 * @author Arnab Bhattacharyya
 */
@ExtendWith(MockitoExtension.class)
public class SlotScheduleServiceTest {
    
    @Mock
    private SlotScheduleRepository repository;
    
    
    @InjectMocks
    private SlotScheduleServiceImpl irrigationScheduleService;
    
    @Spy
    private ModelMapper modelMapper;
    
    @Test
    public void createSchedule_idExists_expectReturnLandConfigure(){
        
        var configureDto = PlotDataProvider.getDefaultConfigureDTO();
        Long id = (long)3;
        Plot land = new Plot();
        this.irrigationScheduleService.createSchedule(new PlotConfiguration());
        verify(repository).save(any());
    }
}
