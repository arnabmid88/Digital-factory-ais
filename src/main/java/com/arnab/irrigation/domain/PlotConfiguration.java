package com.arnab.irrigation.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
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
public class PlotConfiguration {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Pattern(regexp="^[A-Za-z0-9-_]*$",message="Invalid name")
    private String deviceName;
    
    private Date timeSlot;
    
    private Integer intervalInDays;
    
    private Integer durationInMinutes;
        
    private Integer amountOfWater;
    
    private Date nextTimeSlot;
    
    private Date createdOn;
    private Date modifiedOn;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Plot plot;
    
}
