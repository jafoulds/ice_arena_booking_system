package com.sealteam6.domainmodel;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import java.util.List;

/**
 * SENG-330/Fall 2017 - Project Iceman Cometh (Team 6)
 * Group.java
 * Purpose: Group object class - Group of registered/non-registered
 * users associated with one or more rink bookings (e.g. hockey team).
 * The registered user who is the creator of the group is called the
 * "group administrator".
 *
 * @author Team 6
 * @version 1.0 11/26/17
 */

@Data
@Builder
public class Group {

    @Id
    String id;
    String groupName;
    List<String> groupMembers;
    String ownerName;

    /**
     * Purpose: Adds user to group.
     * @param username Username of user to be added.
     */
    public void addUser(String username) {
        groupMembers.add(username);
    }

}
