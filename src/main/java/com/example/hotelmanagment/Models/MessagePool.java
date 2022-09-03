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
    @OneToOne
    @JoinColumn(name = "sender")
    private User sender;
    @OneToOne
    @JoinColumn(name = "receiver")
    private User receiver;
    private String message;
    @Column( columnDefinition = "TIMESTAMP")
    private Date createdOn;
}
