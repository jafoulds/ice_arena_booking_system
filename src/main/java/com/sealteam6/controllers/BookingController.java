package com.sealteam6.controllers;

import com.sealteam6.domainmodel.Booking;
import com.sealteam6.repository.BookingRepository;
import com.sealteam6.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;
    private BookingService bookingService;

    // add a new booking
    @RequestMapping(value = "/addBooking", method=RequestMethod.POST, consumes="application/json")
    @ResponseBody
    public String addBooking(@RequestBody Booking booking) {
        bookingService.createBooking(booking);
        return "redirect:/";
    }

    // get booking by id
    @RequestMapping(value = "/getBooking")
    public Booking getBooking(String id) {
        return bookingRepository.findById(id);
    }

    // show all bookings
    @RequestMapping(value = "/getBookings", method=RequestMethod.GET)
    public List<Booking> getBookings(String id) {
        return bookingRepository.findAll();
    }

    // delete by booking id (JSON)
    @RequestMapping(value = "/cancelBooking", method=RequestMethod.POST, consumes="application/json")
    @ResponseBody
    public String cancelBooking(@RequestBody String id) {
        Booking booking = bookingRepository.findById(id);
        bookingRepository.delete(booking);
        return "Booking Cancelled";
    }

    // delete all bookings in date range
    @RequestMapping(value = "/cancelBookingsInDateRange", method=RequestMethod.GET)
    @ResponseBody
    public void cancelBookingsInDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<Booking> bookings = bookingRepository.findByDateBetween(startDate, endDate);
        for (Booking booking : bookings) {
            bookingRepository.delete(booking);
        }
    }

    // FOR DEBUGGING

    // TEST: delete by Booking ID
    @RequestMapping(value = "/cancelBookingById", method=RequestMethod.GET)
    @ResponseBody
    public String cancelBookingById(@RequestParam String id) {
        Booking booking = bookingRepository.findById(id);
        bookingRepository.delete(booking);
        return "Booking Cancelled";
    }

    // TEST: delete all bookings
    @RequestMapping(value = "/cancelAllBookings", method=RequestMethod.GET)
    @ResponseBody
    public String cancelAllBookings() {
        List <Booking> bookings = bookingRepository.findAll();
        for (Booking booking : bookings) {
            bookingRepository.delete(booking);
        }
        return "All Bookings Cancelled";
    }


}
