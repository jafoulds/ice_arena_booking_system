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

/**
 * SENG-330/Fall 2017 - Project Iceman Cometh (Team 6)
 * BookingController.java
 * Purpose: Booking controller class - group reservation of a rink for a specified time period.
 *
 * @author Team 6
 * @version 1.0 11/26/17
 */

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookingService bookingService;

    /**
     * Purpose: Create new booking.
     * @param booking create booking request (JSON format).
     * @return HTTP status for successful/unsuccessful repository update.
     */
    @RequestMapping(value = "/addBooking", method=RequestMethod.POST, consumes="application/json")
    @ResponseBody
    public ResponseEntity<?> addBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking) ?
            new ResponseEntity<>(booking, HttpStatus.OK):
            new ResponseEntity<>("Booking has conflict.", HttpStatus.CONFLICT);
    }

    /**
     * Purpose: Get booking by id.
     * @param id Requested booking (JSON format).
     * @return Requested booking.
     */
    @RequestMapping(value = "/getBooking", method=RequestMethod.GET)
    public Booking getBooking(String id) {
        return bookingRepository.findById(id);
    }

    /**
     * Purpose: Get all bookings.
     * @return List of all bookings.
     */
    @RequestMapping(value = "/getBookings")
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    /**
     * Purpose: Delete select booking.
     * @param id booking to be deleted (JSON format).
     * @return HTTP status for successful/unsuccessful repository update.
     */
    @RequestMapping(value = "/cancelBooking", method=RequestMethod.GET)
    @ResponseBody
    public HttpStatus cancelBookingById(@RequestParam String id) {
        return bookingService.cancelBooking(id) ? HttpStatus.OK : HttpStatus.GONE;
    }

    /**
     * Purpose: Delete bookings in a date range.
     * @param startDate Start date of range (JSON format).
     * @param endDate End date of range (JSON format).
     * @return HTTP status for successful/unsuccessful repository update.
     */
    @RequestMapping(value = "/cancelBookingsInDateRange", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> cancelBookingsInDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return bookingService.cancelBookingsInDateRange(startDate,endDate) ?
            new ResponseEntity<>(HttpStatus.OK):
            new ResponseEntity<>("Booking does not exist.", HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
    }

    // Manager controls

    /**
     * Purpose: Delete all bookings.
     * @return HTTP status for successful/unsuccessful repository update.
     */
    @RequestMapping(value = "/cancelAllBookings", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?>  cancelAllBookings() {
        List <Booking> bookings = bookingRepository.findAll();
        for (Booking booking : bookings) {
            bookingRepository.delete(booking);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
