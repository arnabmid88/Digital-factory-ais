
package com.arnab.irrigation.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Plot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String code;
    
    private float area;
    
    private String landType;
    
    private Date createdOn;
    private Date modifiedOn;
    
    @OneToMany(mappedBy="land")
    private List<PlotConfiguration> landConfigurations;
}
