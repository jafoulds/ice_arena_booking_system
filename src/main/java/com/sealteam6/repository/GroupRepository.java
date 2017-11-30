package com.sealteam6.repository;

import com.sealteam6.domainmodel.Group;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
/**
 * SENG-330/Fall 2017 - Project Iceman Cometh (Team 6)
 * GroupRepository.java
 * Purpose: Handles group repository queries.
 *
 * @author Team 6
 * @version 1.0 11/26/17
 */
public interface GroupRepository extends MongoRepository<Group, String> {

    Group findByGroupName(String groupName);

    List <Group> findByOwnerName(String ownerName);
}
