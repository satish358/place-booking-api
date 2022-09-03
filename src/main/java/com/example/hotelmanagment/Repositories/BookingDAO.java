package com.example.hotelmanagment.Repositories;

import com.example.hotelmanagment.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingDAO extends JpaRepository<Booking, Long> {
    List<Booking> findBookingsByBookedBy(Long bookedBy);
}
