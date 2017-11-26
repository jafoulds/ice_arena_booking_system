package com.sealteam6.controllers;

import com.sealteam6.domainmodel.Booking;
import com.sealteam6.domainmodel.Rink;
import com.sealteam6.repository.BookingRepository;
import com.sealteam6.repository.RinkRepository;
import com.sealteam6.service.ArenaScheduleService;
import com.sealteam6.service.BookingService;
import com.sealteam6.service.BookingDeserializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(RegisterController.class)
@AutoConfigureMockMvc(secure = false)

public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

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

    private String USERNAME = "fakeUser";
    private String EMAIL = "email@mail.com";
    private String PASSWORD = "password";
    private String GROUP = "group1";
    private String RINK = "rink1";

    @Test
    public void addBooking() throws Exception {
        List<Rink> rinks = rinkRepository.findAll();
        YearMonth yearMonth = YearMonth.of(2000, 1);
        LocalDate date = yearMonth.atDay(1);
        LocalTime openingTime = arenaScheduleService.getOpeningTime(date);
        LocalTime closingTime = arenaScheduleService.getClosingTime(date);
        List<Booking> bookings = new ArrayList<>();
        LocalDateTime startDate = LocalDateTime.of(date, openingTime);
        LocalDateTime endDate = LocalDateTime.of(date, closingTime);
        when(bookingService.findById(anyString())).thenReturn(null);
        MockHttpServletResponse response = this.mockMvc.perform(get("/addBooking"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void getBooking() throws Exception {
    }

    @Test
    public void getBookings() throws Exception {
    }

    @Test
    public void cancelBooking() throws Exception {
    }

    @Test
    public void cancelBookingsInDateRange() throws Exception {
    }

    @Test
    public void cancelBookingById() throws Exception {
    }

    @Test
    public void cancelAllBookings() throws Exception {
    }

}