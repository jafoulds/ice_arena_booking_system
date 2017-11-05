package com.sealteam6.domainmodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class User {

    @Id
    private String id;

    private String username;

    private String emailAddress;

    @JsonIgnore
    private String password;

    private List<String> roles;

    public void addRole(String role) {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        roles.add(role);
    }

    public User() {

    }

    public User(String username, String password, String emailAddress) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
    }

}
