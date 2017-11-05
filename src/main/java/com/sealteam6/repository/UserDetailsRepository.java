package com.sealteam6.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsRepository extends MongoRepository<UserDetails, String> {

    public UserDetails findByUsername(String userName);

}
