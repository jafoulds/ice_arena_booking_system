package com.sealteam6.service;

import com.sealteam6.domainmodel.Group;
import com.sealteam6.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SENG-330/Fall 2017 - Project Iceman Cometh (Team 6)
 * BookingService.java
 * Purpose: Group service class - Service layer for group repository queries.
 *
 * @author Team 6
 * @version 1.0 11/26/17
 */

@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    public boolean userIsInGroup(String username, String groupName) {
        Group group = groupRepository.findByGroupName(groupName);
        return group.getGroupMembers().contains(username);
    }
}
