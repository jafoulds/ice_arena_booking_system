package com.sealteam6.domainmodel;

import lombok.Builder;
import lombok.Value;

import java.time.LocalTime;
import java.util.Collection;
import java.util.Optional;

@Value
@Builder
public class TimeSlot {

    LocalTime startTime;
    LocalTime endTime;
    Rink rink;
    String bookingId;

}
