package com.sealteam6.domainmodel;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collections;

/**
 * SENG-330/Fall 2017 - Project Iceman Cometh (Team 6)
 * Customer.java
 * Purpose: Customer object class - Person that books an ice rink and/or joins a group.
 *
 * @author Team 6
 * @version 1.0 11/26/17
 */

public class Customer extends User {

    private static final String role = "ROLE_USER";

    /**
     * Purpose: Constructor for new customer object.
     * @param username username for new user account.
     * @param password password for new user account.
     * @param emailAddress email address of new user.
     */
    public Customer(String username, String password, String emailAddress) {
        super(username, password, emailAddress, Collections.singletonList(new SimpleGrantedAuthority(role)));
    }


}
