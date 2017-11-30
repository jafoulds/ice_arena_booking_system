package com.sealteam6.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * SENG-330/Fall 2017 - Project Iceman Cometh (Team 6)
 * BookingService.java
 * Purpose: Arena Schedule service class - Service layer
 * for setting the operating hours of the arena.
 *
 * @author Team 6
 * @version 1.0 11/26/17
 */
@Service
public class ArenaScheduleService {

    /**
     * @param date The date to check the opening time.
     * @return The time that the arena opens on the specified date.
     */
    public LocalTime getOpeningTime(LocalDate date) {
        return LocalTime.of(5, 0);
    }

    /**
     * @param date The date to check the closing time.
     * @return The time that the arena closes on the specified date.
     */
    public LocalTime getClosingTime(LocalDate date) {
        return LocalTime.of(20, 0);
    }

    /**
     * @return the length of an increment for booking scheduling.
     */
    public static int getIncrementInMinutes() {
        return 60;
    }
}
