package com.sealteam6.controllers;

import com.sealteam6.domainmodel.Booking;
import com.sealteam6.domainmodel.Rink;
import com.sealteam6.repository.BookingRepository;
import com.sealteam6.repository.RinkRepository;
import com.sealteam6.service.ArenaScheduleService;
import com.sealteam6.service.BookingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    @MockBean
    private BookingService bookingService;

    @MockBean
    private BookingRepository bookingRepository;

    @MockBean
    private RinkRepository rinkRepository;

    @MockBean
    private ArenaScheduleService arenaScheduleService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @Autowired
    private BookingController bookingController;

    private String USERNAME = "fakeUser";
    private String EMAIL = "email@mail.com";
    private String PASSWORD = "password";
    private String GROUP = "group1";
    private String RINK = "rink1";

    @Before
    public void setup() {
        List<Rink> allRinks = new ArrayList<>();
        allRinks.add(new Rink("1"));
        allRinks.add(new Rink("2"));
        when(rinkRepository.findAll()).thenReturn(allRinks);
        when(arenaScheduleService.getOpeningTime(any(LocalDate.class))).thenReturn(LocalTime.of(5, 0));
        when(arenaScheduleService.getClosingTime(any(LocalDate.class))).thenReturn(LocalTime.of(20, 0));
    }

    @Test
    public void addBookingNoConflict() throws Exception {
        Booking booking = generateBooking();
        when(bookingService.createBooking(anyObject())).thenReturn(true);
        ResponseEntity response = bookingController.addBooking(booking);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void addBookingWithConflict() throws Exception {
        Booking booking = generateBooking();
        ResponseEntity response = bookingController.addBooking(booking);
        assertThat(response.getStatusCode(), is(HttpStatus.CONFLICT));
    }

    @Test
    public void getBooking() throws Exception {
        Booking storedBooking = generateBooking();
        when(bookingRepository.findById(RINK)).thenReturn(storedBooking);
        Booking booking = bookingController.getBooking(RINK);
        assertThat(booking, is(storedBooking));

    }

    @Test
    public void cancelBookingSuccess() throws Exception {
        Booking storedBooking = generateBooking();
        when(bookingService.cancelBooking(RINK)).thenReturn(true);
        HttpStatus status = bookingController.cancelBookingById(RINK);
        assertThat(status, is(HttpStatus.OK));
    }

    @Test
    public void cancelBookingFailure() throws Exception {
        Booking storedBooking = generateBooking();
        when(bookingService.cancelBooking(RINK)).thenReturn(false);
        HttpStatus status = bookingController.cancelBookingById(RINK);
        assertThat(status, is(HttpStatus.GONE));
    }

    private Booking generateBooking() {
        List<Rink> rinks = rinkRepository.findAll();
        YearMonth yearMonth = YearMonth.of(2000, 1);
        LocalDate date = yearMonth.atDay(1);
        LocalTime openingTime = arenaScheduleService.getOpeningTime(date);
        LocalTime closingTime = arenaScheduleService.getClosingTime(date);
        LocalDateTime startDate = LocalDateTime.of(date, openingTime);
        LocalDateTime endDate = LocalDateTime.of(date, closingTime);
        return Booking.builder()
            .startDate(startDate)
            .endDate(endDate)
            .rink(rinks.get(0))
            .usernameOfBooker(USERNAME)
            .groupName(GROUP)
            .build();
    }
}