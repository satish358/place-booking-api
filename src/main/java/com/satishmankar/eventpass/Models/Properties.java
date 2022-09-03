package com.satishmankar.eventpass.Models;

import com.satishmankar.eventpass.Enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity @AllArgsConstructor @NoArgsConstructor @Data
public class Properties {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private Long userId;
    private Boolean isAvailable;
    private PropertyType type;
    private Date createdOn;
    private Boolean active;
}
