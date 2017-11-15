package com.sealteam6.domainmodel;

import lombok.Value;

import java.time.LocalTime;
import java.util.Collection;

@Value
public class TimeSlot {

    LocalTime startTime;
    LocalTime endTime;
    Rink rink;

}
