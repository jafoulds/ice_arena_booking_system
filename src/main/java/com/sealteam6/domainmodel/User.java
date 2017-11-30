package com.sealteam6.domainmodel;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

/**
 * SENG-330/Fall 2017 - Project Iceman Cometh (Team 6)
 * User.java
 * Purpose: User object class - Persons registered in the system.
 * Customer – Person that books an ice rink and/or joins a group.
 * Manager – Arena employee that manages rink bookings and maintenance scheduling.
 *
 * @author Team 6
 * @version 1.0 11/26/17
 */

@Getter
@Setter
public class User extends org.springframework.security.core.userdetails.User {

    private String emailAddress;

    /**
     * Purpose: Constructor for new user.
     * @param username create booking request (JSON format).
     * @param password create booking request (JSON format).
     * @param emailAddress create booking request (JSON format).
     */
    public User(String username, String password, String emailAddress, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.emailAddress = emailAddress;
    }
}
