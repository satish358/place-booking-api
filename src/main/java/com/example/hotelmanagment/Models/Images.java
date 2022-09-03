package com.example.hotelmanagment.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity @NoArgsConstructor @AllArgsConstructor @Data
public class Images {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String imageUrl;
    private Long propertyId;
}
