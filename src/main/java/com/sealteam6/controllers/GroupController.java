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

/**
 * SENG-330/Fall 2017 - Project Iceman Cometh (Team 6)
 * GroupController.java
 * Purpose: Group controller class - Group of registered/non-registered
 * users associated with one or more rink bookings (e.g. hockey team).
 * The registered user who is the creator of the group is called the
 * "group administrator".
 *
 * @author Team 6
 * @version 1.0 11/26/17
 */

@RestController
@RequestMapping("/api")
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    /**
     * Purpose: Adds a user to a group.
     * @param username Username of user to be added.
     * @param groupName Group the user will be added to.
     * @return HTTP status for successful/unsuccessful repository update.
     */
    @RequestMapping("/addUserToGroup")
    public HttpStatus addUser(@RequestParam String username, @RequestParam String groupName) {
        Group group = groupRepository.findOne(groupName);
        group.addUser(username);
        groupRepository.save(group);
        return HttpStatus.OK;
    }

    /**
     * Purpose: Create a new booking group.
     * @param groupName Name of the new group.
     * @return HTTP status for successful/unsuccessful repository update.
     */
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

    /**
     * Purpose: Retrieves a group based on group name.
     * @param groupName Group the user will be added to.
     * @return Group object or null if not found.
     */
    @RequestMapping("getGroup")
    public Group getGroup(@RequestParam String groupName) {
        return groupRepository.findByGroupName(groupName);
    }

    /**
     * Purpose: Retrieves a group based on current logged in user.
     * @return List of groups owned by user or null if not found.
     */
    @RequestMapping("getGroupsByOwnerName")
    public List <Group> getGroupsByOwnerName() {
        String ownerName = SecurityContextHolder.getContext().getAuthentication().getName();

        return groupRepository.findByOwnerName(ownerName);
    }

}
