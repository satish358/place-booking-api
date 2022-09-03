package com.example.hotelmanagment.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Booking {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "propertiesId")
    private Properties property;
    @OneToOne
    @JoinColumn(name = "userId")
    private User bookedBy;
    private Date checkInDate;
    private Date checkOutDate;
    private Long numberOfPersons;
    private Long totalPayment;
    private Date bookedOn;
    private Boolean isConfirmed;
}
