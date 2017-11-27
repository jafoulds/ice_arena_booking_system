package com.sealteam6.domainmodel;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import java.util.*;



@Data
@Builder
public class Group {

    @Id
    String id;

    String groupName;

    List<GroupMember> groupMembers;

    String ownerName;

    public void addUser(String username) {
        List<GroupPermission> permissions = new ArrayList<GroupPermission>();
        permissions.add(GroupPermission.STANDARD_USER);
    	GroupMember buddy = new GroupMember(username, permissions);
        groupMembers.add(buddy);
    }


}
