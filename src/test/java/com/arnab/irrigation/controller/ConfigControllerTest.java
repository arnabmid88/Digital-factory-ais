package com.arnab.irrigation.controller;

import com.arnab.irrigation.service.PlotConfigurationService;
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

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
//@Transactional
public class ConfigControllerTest {
    
    @Autowired
    protected MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    protected ObjectMapper objectMapper;
    
    @SpyBean
    protected ModelMapper modelMapper;
    
    @SpyBean
    protected PlotConfigurationService plotConfigurationService;
    
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }
    @Test
    void getPlotConfigurationDetails_validRequest_expectOkContent() throws Exception {
        
        String url = "/api/v1/configuration/";
               
        mvc.perform(get(url))
                .andExpect(status().isOk());

        verify(plotConfigurationService).getAllConfiguration();
    }

    @Test
    void getPlotConfigurationDetailsById_validRequest_expectOkContent() throws Exception {
        String config_id = "63e87e4b89b6236ecccaf494";
        String url = "/api/v1/configuration/"+config_id;

        mvc.perform(get(url))
                .andExpect(status().isOk());

        verify(plotConfigurationService).getConfigurationById(config_id);
    }
    @Test
    void getPlotConfigurationDetailsById_wrongId_expectOkContent() throws Exception {
        String config_id = "63";
        String url = "/api/v1/configuration/"+config_id;

        mvc.perform(get(url))
                .andExpect(status().isOk());

    }
    
  
    
   
    
}
