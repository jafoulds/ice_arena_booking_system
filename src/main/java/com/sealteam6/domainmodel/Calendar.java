package com.sealteam6.domainmodel;

import lombok.Value;
import java.time.YearMonth;
import java.util.List;

/**
 * SENG-330/Fall 2017 - Project Iceman Cometh (Team 6)
 * Calendar.java
 * Purpose: Calendar object class - Used in Calendar views.
 *
 * @author Team 6
 * @version 1.0 11/26/17
 */

@Value
public class Calendar {

    List<CalendarDay> days;
    YearMonth month;

}
