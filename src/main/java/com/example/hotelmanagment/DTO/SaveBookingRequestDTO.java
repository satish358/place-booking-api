package com.example.hotelmanagment.DTO;

import com.example.hotelmanagment.Models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class SaveBookingRequestDTO {
    private Long propertyId;
    private Long bookedByUserId;
    private Date checkInDate;
    private Date checkOutDate;
    private Long numberOfPersons;
    private Long totalPayment;
}
