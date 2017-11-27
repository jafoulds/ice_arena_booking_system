package com.sealteam6.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sealteam6.domainmodel.*;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {

    @Query("{'startDate' : {$gte : ?0}, 'endDate' : {$lte : ?1}}")
    List<Booking> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    Booking findById(String id);

    List <Booking> findByRink(Rink rink);

    List <Booking> findAll();

}