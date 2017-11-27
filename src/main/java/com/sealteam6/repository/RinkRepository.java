package com.sealteam6.repository;

import com.sealteam6.domainmodel.Rink;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RinkRepository extends MongoRepository<Rink, String> {

    Rink findById(String id);

}
