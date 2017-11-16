package com.sealteam6.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sealteam6.domainmodel.*;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;


public interface BookingRepository extends MongoRepository<Booking, String> {

    public List<Booking> findByUserName(String username);

    @Query("{'startDate' : {$gte : ?0}, 'endDate' : {$lte : ?1}}")
    public List<Booking> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    public List<Booking> findByRink(String rinkID);
    
    public List<Booking> findByGroup(String grpID);

}


