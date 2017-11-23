package com.sealteam6.controllers;

import com.sealteam6.domainmodel.Calendar;
import com.sealteam6.domainmodel.CalendarFactory;
import com.sealteam6.domainmodel.User;
import com.sealteam6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.YearMonth;

@RestController
public class CalendarController {

    @Autowired
    CalendarFactory calendarFactory;

    @Autowired
    UserService userService;

    @RequestMapping(value = "api/getCalendar", method = RequestMethod.GET)
    public Calendar getCalendar(@RequestParam int year, @RequestParam int month) {
        return calendarFactory.createCalendarOfAvailableTimeSlots(month, year);
    }

    @RequestMapping(value = "api/getCalendarForUser", method = RequestMethod.GET)
    public Calendar getCalendarForUser(@RequestParam int year, @RequestParam int month) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByUsername(username);
        return calendarFactory.createCalendarForUser(YearMonth.of(year, month), user);
    }
}
