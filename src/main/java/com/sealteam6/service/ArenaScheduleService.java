package com.sealteam6.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

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
