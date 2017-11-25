package com.sealteam6.repository;

import com.sealteam6.domainmodel.Group;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface GroupRepository extends MongoRepository<Group, String> {

    Group findByGroupName(String groupName);

    List <Group> findByOwnerName(String ownerName);
}
