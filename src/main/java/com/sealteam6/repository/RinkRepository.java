package com.sealteam6.repository;

import com.sealteam6.domainmodel.Rink;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * SENG-330/Fall 2017 - Project Iceman Cometh (Team 6)
 * RinkRepository.java
 * Purpose: Interface for Rink repository queries.
 *
 * @author Team 6
 * @version 1.0 11/26/17
 */

public interface RinkRepository extends MongoRepository<Rink, String> {

    Rink findById(String id);

}
