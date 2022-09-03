package com.example.hotelmanagment.Models;

import com.example.hotelmanagment.Enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;
    private String  password;
    private String name;
    private String email;
    private String contact;
    private UserRole role;
    private String profileImageUrl;
}
