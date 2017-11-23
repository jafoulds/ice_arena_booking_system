package com.sealteam6.controllers;

import com.sealteam6.domainmodel.Calendar;
import com.sealteam6.domainmodel.CalendarFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalendarController {

    @Autowired
    CalendarFactory calendarFactory;

    @RequestMapping(value = "api/getCalendar", method = RequestMethod.GET)
    public Calendar getCalendar(@RequestParam int year, @RequestParam int month) {
        return calendarFactory.createCalendarOfAvailableTimeSlots(month, year);
    }
}
