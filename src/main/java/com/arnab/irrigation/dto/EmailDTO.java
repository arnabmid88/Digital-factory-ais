package com.arnab.irrigation.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Arnab Bhattacharyya
 */
@Getter
@Setter
public class EmailDTO {
    
    private String to;
    private String from;
    private String subject;
    private String body;

}
