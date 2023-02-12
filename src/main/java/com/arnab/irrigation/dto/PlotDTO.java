package com.arnab.irrigation.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

/**
 *
 * @author Arnab Bhattacharyya
 */

@Getter
@Setter
public class PlotDTO {
    
    private String _id;
    
    @Size(max=100,message="Land code is too long")
    private String code;
    
    @NotEmpty
    private String plotType;
    
    @Min(1)
    private float area;
    
    private List<ConfigurePlotDTO> plotConfigurations;
    
}
