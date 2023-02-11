package com.arnab.irrigation.dto;

import com.arnab.irrigation.domain.Plot;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Arnab Bhattacharyya
 */

@Getter
@Setter
public class ConfigurePlotDTO {
    
    @NotEmpty
    private String deviceName;
    
    private Date timeSlot;
    
    private Integer durationInMinutes;
    
    @Min(1)
    private Integer intervalInDays;
    
    @Min(10)
    private Integer amountOfWater;

    private List<Plot> plots;
    
}
