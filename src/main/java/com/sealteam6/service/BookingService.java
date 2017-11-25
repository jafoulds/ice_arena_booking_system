package com.sealteam6.service;

import com.sealteam6.domainmodel.Booking;
import com.sealteam6.repository.UserRepository;
import com.sealteam6.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

public class BookingService {

    private BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) { this.bookingRepository = bookingRepository; }

    public void createBooking(Booking booking) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        
        // Rule out date-time conflicts
        bookingRepository.save(booking);
    }

}
