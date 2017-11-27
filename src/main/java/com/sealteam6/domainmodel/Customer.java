package com.sealteam6.domainmodel;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer extends User {

    private static final String role = "ROLE_USER";

    public Customer(String username, String password, String emailAddress) {
        super(username, password, emailAddress, Collections.singletonList(new SimpleGrantedAuthority(role)));
    }


}
