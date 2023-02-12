package com.arnab.irrigation.controller;

import com.arnab.irrigation.dataprovider.PlotDataProvider;
import com.arnab.irrigation.domain.Plot;
import com.arnab.irrigation.dto.PlotDTO;
import com.arnab.irrigation.service.PlotService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



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
               
        mvc.perform(post("/api/v1/plot")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(PlotDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(PlotDto.getCode())));

        verify(PlotService).addPlot(any());
    }
    
    @Test
    void editPlot_validRequest_expectOkContent() throws Exception {
        
        String id = "63e7d658793939669357501b";
        PlotDTO PlotDto = PlotDataProvider.getDefaultPlotDTO();
        Plot Plot = PlotDataProvider.getPlotFromPlotDTO(PlotDto,id);
        
        mvc.perform(put("/api/v1/plot/"+Plot.get_id())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(PlotDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(PlotDto.getCode())));

        verify(PlotService).editPlot(any(),any());
    }
    
    @Test
    void configurePlot_validRequest_expectOkContent() throws Exception {
        String id = "63e7d658793939669357501b";
        var PlotConfigure = PlotDataProvider.getDefaultConfigureDTO();
                
        mvc.perform(post("/api/v1/plot/"+id+"/configure")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(PlotConfigure)))
                .andExpect(status().isOk());

    }
    
    @Test
    void getPlotDetails_validRequest_expectOkContent() throws Exception {
        
        String url = "/api/v1/plot/";
               
        mvc.perform(get(url))
                .andExpect(status().isOk());

        verify(PlotService).getAllPlots();
    }
    
    
  
    
   
    
}
