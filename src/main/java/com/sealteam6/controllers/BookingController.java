package com.sealteam6.controllers;

import org.springframework.stereotype.Controller;

import com.sealteam6.domainmodel.Booking;

import com.sealteam6.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    // add a new booking
    @RequestMapping(value = "/api/addBooking", method=RequestMethod.POST, consumes="application/json")
    @ResponseBody
    public void addBooking(@RequestBody Booking booking) {
        bookingRepository.save(booking);
    }

    // get booking by id
    @RequestMapping(value = "/api/getBooking")
    public Booking getBooking(String id) {
        return bookingRepository.findById(id);
    }

    // show all bookings
    @RequestMapping(value = "/api/showBookings", method=RequestMethod.GET)
    public List<Booking> showBookings(String id) {
        return bookingRepository.findAll();
    }

    // delete by booking id (JSON)
    @RequestMapping(value = "/api/cancelBooking", method=RequestMethod.POST, consumes="application/json")
    @ResponseBody
    public String cancelBooking(@RequestBody String id) {
        Booking booking = bookingRepository.findById(id);
        bookingRepository.delete(booking);
        return "Booking Cancelled";
    }

    // delete all bookings in date range
    @RequestMapping(value = "/api/cancelBookingsInDateRange", method=RequestMethod.GET)
    @ResponseBody
    public void cancelBookingsInDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<Booking> bookings = bookingRepository.findByDateBetween(startDate, endDate);
        for (Booking booking : bookings) {
            bookingRepository.delete(booking);
        }
    }

    // FOR DEBUGGING

    // TEST: delete by Booking ID
    @RequestMapping(value = "/api/cancelBookingById", method=RequestMethod.GET)
    @ResponseBody
    public String cancelBookingById(@RequestParam String id) {
        Booking booking = bookingRepository.findById(id);
        bookingRepository.delete(booking);
        return "Booking Cancelled";
    }

    // TEST: delete all bookings
    @RequestMapping(value = "/api/cancelAllBookings", method=RequestMethod.GET)
    @ResponseBody
    public String cancelAllBookings() {
        List <Booking> bookings = bookingRepository.findAll();
        for (Booking booking : bookings) {
            bookingRepository.delete(booking);
        }
        return "All Bookings Cancelled";
    }


}
