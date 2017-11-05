package com.sealteam6.repository;

import com.sealteam6.domainmodel.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    public User findByUsername(String userName);
}
