package com.sealteam6.service;

import com.sealteam6.domainmodel.Group;
import com.sealteam6.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    public boolean userIsInGroup(String username, String groupName) {
        Group group = groupRepository.findByGroupName(groupName);
        return group.getGroupMembers().contains(username);
    }
}
