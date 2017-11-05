package com.sealteam6.domainmodel;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    @Id
    private String id;

    private String username;

    private String password;

    private List<String> roles;

    public void addRole(String role) {
        if (roles == null) {
            roles = new ArrayList<String>();
        }
        roles.add(role);
    }

    public UserDetailsImpl() {
    }

    public UserDetailsImpl(String username, String password, Collection<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = new ArrayList<>(roles);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
