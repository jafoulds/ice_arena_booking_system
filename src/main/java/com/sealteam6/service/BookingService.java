package com.sealteam6.service;

import com.sealteam6.domainmodel.Booking;
import com.sealteam6.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) { this.bookingRepository = bookingRepository; }

    // Create new booking
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

    // Cancel booking
    public boolean cancelBooking(String id) {
        Booking booking = bookingRepository.findById(id);
        if (booking!=null) {
            bookingRepository.delete(booking);
            return true;
        } else {return false;}
    }

    // Cancel bookings in date range
    public boolean cancelBookingsInDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<Booking> bookings = bookingRepository.findByDateBetween(startDate, endDate);
        if (bookings!=null) {
            for (Booking booking : bookings) { bookingRepository.delete(booking); }
            return true;
        } else {return false;}
    }

    // Find booking by id
    public Booking findById(String id) {
        return bookingRepository.findById(id);
    }

}
