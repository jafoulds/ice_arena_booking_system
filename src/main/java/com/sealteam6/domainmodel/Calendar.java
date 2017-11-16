package com.sealteam6.domainmodel;

import lombok.Getter;
import lombok.Value;

import java.time.YearMonth;
import java.util.List;

@Value
public class Calendar {

    List<CalendarDay> days;
    YearMonth month;


}
