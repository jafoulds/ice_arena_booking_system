package com.sealteam6.security;

import com.sealteam6.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
/**
 * SENG-330/Fall 2017 - Project Iceman Cometh (Team 6)
 * AuthenticationService.java
 * Purpose: Authenticate users.
 *
 * @author Team 6
 * @version 1.0 11/26/17
 */
@Service
public class AuthenticationService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userRepository.findByUsername(username);
        if (userDetails == null) {
            throw new UsernameNotFoundException(String.format("User %s does not exist", username));
        }
        return userDetails;
    }
}
