package com.sealteam6.domainmodel;

import lombok.Value;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Date;
import java.util.List;

@Value
public class CalendarDay {

    LocalDate date;
    List<TimeSlot> availableTimeSlots;

}
