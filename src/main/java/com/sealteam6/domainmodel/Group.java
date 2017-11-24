package com.sealteam6.domainmodel;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Builder
public class Group {

    @Id
    String id;

    String groupName;

    List<String> groupMembers;

    String ownerName;

    public void addUser(String username) {
        groupMembers.add(username);
    }


}
