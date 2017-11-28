package com.sealteam6.domainmodel;

import lombok.Data;
import lombok.Value;
import org.springframework.data.annotation.Id;
import java.util.List;

@Value
public class GroupMember{

	final String username;
	List<GroupPermission> permissions;


}
