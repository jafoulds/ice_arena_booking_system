package com.sealteam6.domainmodel;

import org.springframework.data.annotation.Id;
import java.util.List;


public enum GroupPermission {

	MAKE_BOOKING, 
	ADD_USER,
	REMOVE_USER,
	MAKE_PAYMENT,
	STANDARD_USER;

}