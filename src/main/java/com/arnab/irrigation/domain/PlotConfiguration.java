package com.arnab.irrigation.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.Pattern;
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
public class PlotConfiguration {
    
    @Id
    private ObjectId _id;
    
    @Pattern(regexp="^[A-Za-z0-9-_]*$",message="Invalid name")
    private String deviceName;
    
    private Date timeSlot;
    
    private Integer intervalInDays;
    
    private Integer durationInMinutes;
        
    private Integer amountOfWater;
    
    private Date nextTimeSlot;
    
    private Date createdOn;
    private Date modifiedOn;
    
}
