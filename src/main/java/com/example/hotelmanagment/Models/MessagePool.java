package com.example.hotelmanagment.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessagePool {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long sender;
    private Long receiver;
    private String message;
    @Column( columnDefinition = "TIMESTAMP")
    private Date createdOn;
}
