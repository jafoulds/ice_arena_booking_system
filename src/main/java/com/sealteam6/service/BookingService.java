package com.sealteam6.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import com.sealteam6.domainmodel.Booking;
import com.sealteam6.domainmodel.GroupBooking;
import com.sealteam6.domainmodel.Rink;
import com.sealteam6.repository.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> findBookingsByUsername(String username) {
        return bookingRepository.findByUserName(username);
    }

    public List<Booking> findBookingsByGroup(String grpID) {
        return bookingRepository.findByGroup(grpID);
    }

    public void save(Booking booking) {
    		bookingRepository.save(booking);
    }
}
