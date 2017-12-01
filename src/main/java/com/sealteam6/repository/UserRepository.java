package com.sealteam6.repository;

import com.sealteam6.domainmodel.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * SENG-330/Fall 2017 - Project Iceman Cometh (Team 6)
 * UserRepository.java
 * Purpose: Handles user repository queries.
 *
 * @author Team 6
 * @version 1.0 11/26/17
 */

public interface UserRepository extends MongoRepository<User, String> {
    public User findByUsername(String userName);
}
