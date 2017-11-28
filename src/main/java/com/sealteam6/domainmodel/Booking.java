package com.sealteam6.domainmodel;

import com.sealteam6.service.BookingDeserializer;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;

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



