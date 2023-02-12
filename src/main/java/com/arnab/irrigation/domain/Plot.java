
package com.arnab.irrigation.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;


@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Plot {
    @Id
    private ObjectId _id;
    
    private String code;
    
    private float area;
    
    private String plotType;
    
    private Date createdOn;
    private Date modifiedOn;
    
    @DBRef
    private List<PlotConfiguration> plotConfigurations;
}
