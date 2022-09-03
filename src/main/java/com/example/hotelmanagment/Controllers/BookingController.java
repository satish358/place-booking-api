package com.example.hotelmanagment.Controllers;

import com.example.hotelmanagment.DTO.BasicResponseDTO;
import com.example.hotelmanagment.DTO.SaveBookingRequestDTO;
import com.example.hotelmanagment.Models.Booking;
import com.example.hotelmanagment.Models.Properties;
import com.example.hotelmanagment.Repositories.BookingDAO;
import com.example.hotelmanagment.Repositories.PropertiesDAO;
import com.example.hotelmanagment.Repositories.UserDAO;
import com.example.hotelmanagment.Services.DefaultEmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("api/booking")
public class BookingController {

    @Autowired
    BookingDAO bookingDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    DefaultEmailService defaultEmailService;

    @Autowired
    PropertiesDAO propertiesDAO;
    @PostMapping("/save")
    @Operation(summary = "Used to make booking", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<BasicResponseDTO<Booking>> saveBooking(@RequestBody SaveBookingRequestDTO saveBookingRequestDTO) {
        Booking booking = new Booking();
        Properties property = propertiesDAO.getById(saveBookingRequestDTO.getPropertyId());

        booking.setBookedBy(userDAO.getById(saveBookingRequestDTO.getBookedByUserId()));
        booking.setBookedOn(new Date());
        booking.setProperty(property);
        booking.setCheckInDate(saveBookingRequestDTO.getCheckInDate());
        booking.setCheckOutDate(saveBookingRequestDTO.getCheckOutDate());
        booking.setNumberOfPersons(saveBookingRequestDTO.getNumberOfPersons());
        booking.setTotalPayment(saveBookingRequestDTO.getTotalPayment());
        booking.setIsConfirmed(false);
        bookingDAO.save(booking);
        defaultEmailService.sendSimpleEmail(property.getUser().getEmail(), "Your property is booked", "Hello sir i'm exited to share this news, you propery is book");
        return ResponseEntity.ok().body(new BasicResponseDTO<>(true, "Booking added", null));
    }

    @GetMapping("/{bookingId}")
    @Operation(summary = "Used get booking", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<BasicResponseDTO<Booking>> getBookingByUserId(@PathVariable Long bookingId) {
        return new ResponseEntity<>(new BasicResponseDTO<>(true, "all data", bookingDAO.getById(bookingId)),HttpStatus.OK);
    }
    @GetMapping("/confirm/{bookingId}")
    @Operation(summary = "Used get booking", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<BasicResponseDTO<Booking>> confirmBooking(@PathVariable Long bookingId) {
        Booking booking = bookingDAO.getById(bookingId);
        booking.setIsConfirmed(true);
        bookingDAO.save(booking);
        return new ResponseEntity<>(new BasicResponseDTO<>(true, "Booking confirmed",booking ),HttpStatus.OK);
    }
}
