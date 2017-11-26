package com.sealteam6.controllers;

import com.sealteam6.domainmodel.Booking;
import com.sealteam6.repository.BookingRepository;
import com.sealteam6.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookingService bookingService;

    // add new booking
    @RequestMapping(value = "/addBooking", method=RequestMethod.POST, consumes="application/json")
    @ResponseBody
    public ResponseEntity<?> addBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking) ?
            new ResponseEntity<>(booking, HttpStatus.OK):
            new ResponseEntity<>("Booking has conflict.", HttpStatus.CONFLICT);
    }

    // get booking by id
    @RequestMapping(value = "/getBooking", method=RequestMethod.GET)
    public Booking getBooking(String id) {
        return bookingRepository.findById(id);
    }

    // get all bookings
    @RequestMapping(value = "/getBookings")
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    // delete by Booking ID
    @RequestMapping(value = "/cancelBooking", method=RequestMethod.GET)
    @ResponseBody
    public HttpStatus cancelBookingById(@RequestParam String id) {
        return bookingService.cancelBooking(id) ? HttpStatus.OK : HttpStatus.GONE;
    }

    // delete all bookings in date range
    @RequestMapping(value = "/cancelBookingsInDateRange", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> cancelBookingsInDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return bookingService.cancelBookingsInDateRange(startDate,endDate) ?
            new ResponseEntity<>(HttpStatus.OK):
            new ResponseEntity<>("Booking does not exist.", HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
    }

    // Test controls

    // delete all bookings
    @RequestMapping(value = "/cancelAllBookings", method=RequestMethod.GET)
    @ResponseBody
    public HttpStatus cancelAllBookings() {
        List <Booking> bookings = bookingRepository.findAll();
        for (Booking booking : bookings) {
            bookingRepository.delete(booking);
        }
        return HttpStatus.OK;
    }

    // get all bookings
    @RequestMapping(value = "/getAllBookings", method=RequestMethod.GET)
    @ResponseBody
    public List <Booking> getAllBookings() {
        return bookingRepository.findAll();
    }


}
