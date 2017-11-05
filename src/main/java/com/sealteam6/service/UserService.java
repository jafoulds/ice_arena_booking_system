package com.sealteam6.service;

import com.sealteam6.domainmodel.User;
import com.sealteam6.domainmodel.UserDetailsImpl;
import com.sealteam6.repository.UserDetailsRepository;
import com.sealteam6.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserDetailsRepository userDetailsRepository;

    private UserRepository userRepository;

    @Autowired
    public UserService(UserDetailsRepository userDetailsRepository, UserRepository userRepository) {
        this.userDetailsRepository = userDetailsRepository;
        this.userRepository = userRepository;
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void save(User user) {
        userRepository.save(user);

        UserDetails userDetails = new UserDetailsImpl(user.getUsername(), user.getPassword(), user.getRoles());
        userDetailsRepository.save(userDetails);
    }
}
