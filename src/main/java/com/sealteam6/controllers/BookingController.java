package com.sealteam6.controllers;

import com.sealteam6.domainmodel.Booking;
import com.sealteam6.repository.BookingRepository;
import com.sealteam6.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;
    private BookingService bookingService;

    // add new booking
    @RequestMapping(value = "/addBooking", method=RequestMethod.POST, consumes="application/json")
    @ResponseBody
    public HttpStatus addBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking) ? HttpStatus.OK : HttpStatus.CONFLICT;
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
    public HttpStatus cancelBooking(@RequestBody String id) {
        return bookingService.cancelBooking(id) ? HttpStatus.OK : HttpStatus.NOT_ACCEPTABLE;
    }

    // delete all bookings in date range
    @RequestMapping(value = "/cancelBookingsInDateRange", method=RequestMethod.GET)
    @ResponseBody
    public HttpStatus cancelBookingsInDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return bookingService.cancelBookingsInDateRange(startDate,endDate) ? HttpStatus.OK : HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE;
    }

    // Manager controls

    // Manager: delete by Booking ID
    @RequestMapping(value = "/cancelBookingById", method=RequestMethod.GET)
    @ResponseBody
    public HttpStatus cancelBookingById(@RequestParam String id) {
        return bookingService.cancelBooking(id) ? HttpStatus.OK : HttpStatus.NOT_ACCEPTABLE;
    }

    // Manager: delete all bookings
    @RequestMapping(value = "/cancelAllBookings", method=RequestMethod.GET)
    @ResponseBody
    public HttpStatus cancelAllBookings() {
        List <Booking> bookings = bookingRepository.findAll();
        for (Booking booking : bookings) {
            bookingRepository.delete(booking);
        }
        return HttpStatus.OK;
    }


}
