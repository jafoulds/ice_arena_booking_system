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

/**
 * SENG-330/Fall 2017 - Project Iceman Cometh (Team 6)
 * CalendarController.java
 * Purpose: Calendar controller class - Searchable timetable (listing) of
 * rink bookings provided to users.
 *
 * @author Team 6
 * @version 1.0 11/26/17
 */

@RestController
public class CalendarController {

    @Autowired
    CalendarFactory calendarFactory;

    @Autowired
    UserService userService;

    /**
     * Purpose: Retrives calendar for requested year and month.
     * @param year Requested calendar year.
     * @param month Requested calendar month.
     * @return HTTP status for successful/unsuccessful repository update.
     */
    @RequestMapping(value = "api/getCalendar", method = RequestMethod.GET)
    public Calendar getCalendar(@RequestParam int year, @RequestParam int month) {
        return calendarFactory.createCalendarOfAvailableTimeSlots(month, year);
    }

    /**
     * Purpose: Retrieves calendar for specific user.
     * @param year Requested calendar year.
     * @param month Requested calendar month.
     * @return calendar for user based on requested year and month.
     */
    @RequestMapping(value = "api/getCalendarForUser", method = RequestMethod.GET)
    public Calendar getCalendarForUser(@RequestParam int year, @RequestParam int month) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByUsername(username);
        return calendarFactory.createCalendarForUser(YearMonth.of(year, month), user);
    }
}
