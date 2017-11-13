package com.sealteam6.domainmodel;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
public class User extends org.springframework.security.core.userdetails.User {

    private String emailAddress;

    public User(String username, String password, String emailAddress, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.emailAddress = emailAddress;
    }





}
