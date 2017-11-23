package com.sealteam6.domainmodel;

import lombok.Value;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Date;
import java.util.List;

@Value
public class CalendarDay implements Comparable<CalendarDay> {

    LocalDate date;
    List<TimeSlot> availableTimeSlots;

    @Override
    public int compareTo(CalendarDay o) {
        return date.compareTo(o.getDate());
    }
}
