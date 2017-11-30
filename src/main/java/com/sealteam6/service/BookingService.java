package com.sealteam6.service;

import com.sealteam6.domainmodel.Booking;
import com.sealteam6.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * SENG-330/Fall 2017 - Project Iceman Cometh (Team 6)
 * BookingService.java
 * Purpose: Booking service class - Service layer for booking repository requests.
 *
 * @author Team 6
 * @version 1.0 11/26/17
 */

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;


    @Autowired
    public BookingService(BookingRepository bookingRepository) { this.bookingRepository = bookingRepository; }

    /**
     * Purpose: Create new booking.
     * @param booking The booking to be added to the calendar.
     * @return Boolean for successful repository save.
     */
    public Boolean createBooking(Booking booking) {

        // Check for booking conflicts
        List<Booking> concurrentBookings = bookingRepository.findByDateBetween(booking.getStartDate(), booking.getEndDate());

        // Filter concurrent bookings by rink
        if (!concurrentBookings.isEmpty()) {
            concurrentBookings = concurrentBookings.stream().filter(b->b.getRink()
                    .equals(booking.getRink())).collect(Collectors.toList());
        }

        if (concurrentBookings.isEmpty()) {
            bookingRepository.save(booking);
            return true;
        } else {return false;}
    }

    /**
     * Purpose: Cancel an existing booking.
     * @param id The id of the booking to be cancelled.
     * @return Boolean for successful repository deletion.
     */
    public boolean cancelBooking(String id) {
        Booking booking = bookingRepository.findById(id);
        if (booking!=null) {
            bookingRepository.delete(booking);
            return true;
        } else {return false;}
    }

    /**
     * Purpose: Cancel an existing bookings within a date range.
     * @param startDate The start date of the booking to be cancelled.
     * @param endDate The end date of the booking to be cancelled.
     * @return Boolean for successful repository deletion.
     */
    public boolean cancelBookingsInDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<Booking> bookings = bookingRepository.findByDateBetween(startDate, endDate);
        if (bookings!=null) {
            for (Booking booking : bookings) { bookingRepository.delete(booking); }
            return true;
        } else {return false;}
    }

    /**
     * Purpose: Find a booking by id value.
     * @param id The id of the booking to be cancelled.
     * @return Found Booking object or null for not found.
     */
    public Booking findById(String id) {
        return bookingRepository.findById(id);
    }

}
