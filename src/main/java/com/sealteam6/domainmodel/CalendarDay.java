package com.sealteam6.domainmodel;

import lombok.Value;

import java.time.LocalDate;
import java.util.List;

/**
 * SENG-330/Fall 2017 - Project Iceman Cometh (Team 6)
 * CalendarDay.java
 * Purpose: CalendarDay object class - Used in Calendar views.
 *
 * @author Team 6
 * @version 1.0 11/26/17
 */

@Value
public class CalendarDay implements Comparable<CalendarDay> {

    LocalDate date;
    List<TimeSlot> availableTimeSlots;

    @Override
    public int compareTo(CalendarDay o) {
        return date.compareTo(o.getDate());
    }
}
