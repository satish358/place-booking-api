package com.example.hotelmanagment.DTO;

import com.example.hotelmanagment.Models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class LoginResponseDTO {
    private String token;
    private String name;
    private String email;
    private String contact;
    private User user;
}
