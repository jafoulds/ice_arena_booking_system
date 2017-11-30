package com.sealteam6.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sealteam6.domainmodel.*;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
/**
 * SENG-330/Fall 2017 - Project Iceman Cometh (Team 6)
 * BookingRepository.java
 * Purpose: Handles booking repository queries.
 *
 * @author Team 6
 * @version 1.0 11/26/17
 */
public interface BookingRepository extends MongoRepository<Booking, String> {

    @Query("{'startDate' : {$gte : ?0}, 'endDate' : {$lte : ?1}}")
    List<Booking> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    Booking findById(String id);

    List <Booking> findByRink(Rink rink);

    List <Booking> findAll();

}