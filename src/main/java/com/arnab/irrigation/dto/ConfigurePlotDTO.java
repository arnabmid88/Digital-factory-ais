package com.arnab.irrigation.dto;

import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

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
    
}
