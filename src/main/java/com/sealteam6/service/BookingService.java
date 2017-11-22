package com.sealteam6.service;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.core.JsonParser;
import com.sealteam6.domainmodel.Booking;
import com.sealteam6.domainmodel.Rink;
import com.sealteam6.repository.RinkRepository;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.io.IOException;

public class BookingService extends JsonDeserializer<Booking> {

    private RinkRepository rinkRepository;

    @Override
    public Booking deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        // Convert dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime startDate = LocalDateTime.parse(node.get("startDate").asText(), formatter);
        LocalDateTime endDate = LocalDateTime.parse(node.get("endDate").asText(), formatter);

        Rink rink = new Rink(node.get("rink").asText());
        String usernameOfBooker = node.get("usernameOfBooker").asText();
        String groupName = node.get("groupName").asText();

        return new Booking(startDate, endDate, rink, usernameOfBooker, groupName);
    }
}
