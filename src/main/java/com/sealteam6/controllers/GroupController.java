package com.sealteam6.controllers;


import com.sealteam6.domainmodel.Group;
import com.sealteam6.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;


    @RequestMapping("/addUserToGroup")
    public HttpStatus addUser(@RequestParam String username, @RequestParam String groupName) {
        Group group = groupRepository.findOne(groupName);
        group.addUser(username);
        groupRepository.save(group);
        return HttpStatus.OK;
    }

    @RequestMapping("/createGroup")
    public HttpStatus createGroup(@RequestParam String groupName) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<String> groupMembers = new ArrayList<>();
        groupMembers.add(username);


        Group group = Group.builder()
                .groupName(groupName)
                .groupMembers(groupMembers)
                .ownerName(username)
                .build();
        groupRepository.save(group);
        return HttpStatus.OK;
    }

    @RequestMapping("getGroup")
    public Group getGroup(@RequestParam String groupName) {
        return groupRepository.findByGroupName(groupName);
    }

    @RequestMapping("getGroupsByOwnerName")
    public List <Group> getGroupsByOwnerName() {
        String ownerName = SecurityContextHolder.getContext().getAuthentication().getName();

        return groupRepository.findByOwnerName(ownerName);
    }

}