package com.sealteam6.controllers;


import com.sealteam6.domainmodel.Group;
import com.sealteam6.domainmodel.GroupMember;
import com.sealteam6.domainmodel.GroupPermission;
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

        List<GroupMember> groupMembers = new ArrayList<>();
        //code taken from group.java -> could be redundant and changed to be somewhere else
        List<GroupPermission> permissions = new ArrayList<GroupPermission>();
        permissions.add(GroupPermission.STANDARD_USER);
        permissions.add(GroupPermission.MAKE_BOOKING);
        permissions.add(GroupPermission.ADD_USER);
        permissions.add(GroupPermission.REMOVE_USER);
        permissions.add(GroupPermission.MAKE_PAYMENT);
        GroupMember buddy = new GroupMember(username, permissions);

        groupMembers.add(buddy); //need to change this from username into a group member object


        Group group = Group.builder()
                .groupName(groupName)
                .groupMembers(groupMembers)
                .ownerName(username)
                .build();
        groupRepository.save(group);
        return HttpStatus.OK;
    }

    @RequestMapping("/getGroup")
    public Group getGroup(@RequestParam String groupName) {
        return groupRepository.findByGroupName(groupName);
    }

    @RequestMapping("getGroupsByOwnerName")
    public List <Group> getGroupsByOwnerName() {
        String ownerName = SecurityContextHolder.getContext().getAuthentication().getName();

        return groupRepository.findByOwnerName(ownerName);
    }

    @RequestMapping("/getListOfUserGroups")
    public List<Group> getListOfUserGroups(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(username);
        System.out.println(groupRepository.getListOfUserGroups(username));
        return groupRepository.getListOfUserGroups(username);  
    }
    @RequestMapping("/findAll")
    public List<Group> findAll(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return groupRepository.findAll();  
    }

}
