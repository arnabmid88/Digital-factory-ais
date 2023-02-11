package com.arnab.irrigation.domain;

import com.arnab.irrigation.domain.enums.DeviceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author Arnab Bhattacharyya
 */
@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SlotSchedule {
    
    @Id
    private String id;
         
    ///private Date timeSlot;
    
    private Integer durationInMinutes;
    
    private Integer amountOfWater;
    
    @Enumerated(EnumType.STRING)
    private DeviceStatus status;
       
    @ManyToOne(fetch = FetchType.LAZY)
    private PlotConfiguration plotConfiguration;
    
    private Date createdOn;
    private Date modifiedOn;
}
