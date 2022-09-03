package com.example.hotelmanagment.Models;

import com.example.hotelmanagment.Enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity @AllArgsConstructor @NoArgsConstructor @Data
public class Properties {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private Long userId;
    private Long price;
    private String bannerUrl;
    private PropertyType type;
    private Date createdOn;
    private Boolean active;
}
