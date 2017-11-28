package com.sealteam6.service;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.core.JsonParser;
import com.sealteam6.domainmodel.Booking;
import com.sealteam6.domainmodel.Rink;
import com.sealteam6.repository.RinkRepository;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class BookingDeserializer extends JsonDeserializer<Booking> {

    @Autowired
    private RinkRepository rinkRepository;

    @Override
    public Booking deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        // Convert dates (BAD FIX: -8 hours to adjust for timezone difference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime startDate = LocalDateTime.parse(node.get("startDate").asText(), formatter);
        LocalDateTime endDate = LocalDateTime.parse(node.get("endDate").asText(), formatter);

        // Get current user as group owner
        String usernameOfBooker = SecurityContextHolder.getContext().getAuthentication().getName();

        Rink rink = rinkRepository.findOne(node.get("rink").asText());
        String groupName = node.get("groupName").asText();

        return Booking.builder()
                .startDate(startDate)
                .endDate(endDate)
                .rink(rink)
                .usernameOfBooker(usernameOfBooker)
                .groupName(groupName)
                .build();
    }
}
