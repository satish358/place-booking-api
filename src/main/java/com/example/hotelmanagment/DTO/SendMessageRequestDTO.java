package com.example.hotelmanagment.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data
public class SendMessageRequestDTO {
    private Long sender;
    private Long receiver;
    private String message;
}
