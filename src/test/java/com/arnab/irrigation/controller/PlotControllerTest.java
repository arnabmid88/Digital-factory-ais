package com.arnab.irrigation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.arnab.irrigation.dataprovider.PlotDataProvider;
import com.arnab.irrigation.domain.Plot;
import com.arnab.irrigation.dto.PlotDTO;
import com.arnab.irrigation.service.PlotService;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;



@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
//@Transactional
public class PlotControllerTest {
    
    @Autowired
    protected MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    protected ObjectMapper objectMapper;
    
    @SpyBean
    protected ModelMapper modelMapper;
    
    @SpyBean
    protected PlotService PlotService;
    
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }
    
    
    @Test
    void addPlot_validRequest_expectOkContent() throws Exception {
        
        PlotDTO PlotDto = PlotDataProvider.getDefaultPlotDTO();
               
        mvc.perform(post("/api/v1/Plot")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(PlotDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(PlotDto.getCode())));

        verify(PlotService).addPlot(any());
    }
    
    @Test
    void editPlot_validRequest_expectOkContent() throws Exception {
        
        Long id = (long)3;
        PlotDTO PlotDto = PlotDataProvider.getDefaultPlotDTO();
        Plot Plot = PlotDataProvider.getPlotFromPlotDTO(PlotDto,id);
        
        mvc.perform(put("/api/v1/Plot/"+Plot.getId())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(PlotDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(PlotDto.getCode())));

        verify(PlotService).editPlot(any(),any());
    }
    
    @Test
    void configurePlot_validRequest_expectOkContent() throws Exception {
        Long id = (long)4;
        ///PlotDTO PlotDto = PlotDataProvider.getDefaultPlotDTO();
        var PlotConfigure = PlotDataProvider.getDefaultConfigureDTO();
                
        mvc.perform(put("/api/v1/Plot/"+id+"/configure"))
                .andExpect(status().isOk());

        
        verify(PlotService).configurePlot(PlotConfigure, id);
    }
    
    @Test
    void getPlotDetails_validRequest_expectOkContent() throws Exception {
        
        String url = "/api/v1/Plot/";
               
        mvc.perform(get(url))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$.batteryLevel", is(BATTERY_LEVEL)));
        
        verify(PlotService).getAllPlots();
    }
    
    
  
    
   
    
}
