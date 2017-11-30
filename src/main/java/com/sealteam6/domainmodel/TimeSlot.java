package com.sealteam6.domainmodel;

import lombok.Builder;
import lombok.Value;

import java.time.LocalTime;

/**
 * SENG-330/Fall 2017 - Project Iceman Cometh (Team 6)
 * TimeSlot.java
 * Purpose: TimeSlot object class - Encodes booking times in calendar view.
 *
 * @author Team 6
 * @version 1.0 11/26/17
 */

@Value
@Builder
public class TimeSlot {

    LocalTime startTime;
    LocalTime endTime;
    Rink rink;
    String bookingId;

}
