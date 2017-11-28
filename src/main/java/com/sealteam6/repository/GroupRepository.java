package com.sealteam6.repository;

import com.sealteam6.domainmodel.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface GroupRepository extends MongoRepository<Group, String> {

    Group findByGroupName(String groupName);


    List <Group> findByOwnerName(String ownerName);

    @Query("{'groupMembers': {$elemMatch: {'username': ?0 } } }")
  	List <Group> getListOfUserGroups(String userName);

    List <Group> findAll();

}
