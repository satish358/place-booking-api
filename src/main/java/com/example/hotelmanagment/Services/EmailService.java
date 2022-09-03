package com.example.hotelmanagment.Services;

public interface EmailService {
    void sendSimpleEmail(String toAddress, String subject, String message);
}
