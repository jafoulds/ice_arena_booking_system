package com.sealteam6.security;

import com.sealteam6.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {


    private UserDetailsRepository userDetailsRepository;

    @Autowired
    public AuthenticationService(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userDetailsRepository.findByUsername(username);
        if (userDetails == null) {
            throw new UsernameNotFoundException(String.format("User %s does not exist", username));
        }
        return userDetails;
    }
}
