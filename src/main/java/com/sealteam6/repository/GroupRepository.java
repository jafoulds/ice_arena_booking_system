package com.sealteam6.repository;

import com.sealteam6.domainmodel.Group;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface GroupRepository extends MongoRepository<Group, String> {

    Group findByGroupName(String groupName);

    List <Group> findByOwnerName(String ownerName);

    @Query("{find: 'group', filter: {groupMembers: {$elemMatch: {username: ?0 } } } } ")
    public List <Group> getListOfUserGroups(String userName);
}
