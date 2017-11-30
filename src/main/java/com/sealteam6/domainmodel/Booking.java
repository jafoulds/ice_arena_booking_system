package com.sealteam6.domainmodel;

import com.sealteam6.service.BookingDeserializer;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;

/**
 * SENG-330/Fall 2017 - Project Iceman Cometh (Team 6)
 * Booking.java
 * Purpose: Booking object class - group reservation of a rink for a specified time period.
 *
 * @author Team 6
 * @version 1.0 11/26/17
 */

@Data
@Builder
@JsonDeserialize(using = BookingDeserializer.class)
public class Booking implements Comparable<Booking> {

    @Id
    private String id;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Rink rink;
    private String usernameOfBooker;
    private String groupName;

    @Override
    public int compareTo(Booking o) {
        if (startDate.equals(o.startDate)) {
            return endDate.compareTo(o.endDate);
        }
        return startDate.compareTo(o.startDate);
    }
}



