package com.example.hotelmanagment.Models;

import com.example.hotelmanagment.Enums.FeatureEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class PropertyFeature {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private FeatureEnum feature;
    private Long propertyId;
}
