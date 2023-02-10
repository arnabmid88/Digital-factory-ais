package com.arnab.irrigation.domain;

import com.arnab.irrigation.domain.enums.DeviceStatus;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
         
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
