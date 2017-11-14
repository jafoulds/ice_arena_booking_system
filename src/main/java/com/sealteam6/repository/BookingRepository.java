package com.sealteam6.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;

import com.sealteam6.domainmodel.*;

// import java.util.Date;
import java.util.List;


public interface BookingRepository extends MongoRepository<Booking, String> {

    public List<Booking> findByUserName(String username);
       
    //public List<Booking> findByDateBetween(String startDate, String endDate);

    //public List<Booking> findByRink(String rinkID);
    
    public List<Booking> findByGroup(String grpID);

}


