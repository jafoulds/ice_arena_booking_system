package com.sealteam6.service;

import com.sealteam6.domainmodel.User;
import com.sealteam6.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SENG-330/Fall 2017 - Project Iceman Cometh (Team 6)
 * UserService.java
 * Purpose: User service class - Service layer for creating new users
 * and repository queries.
 *
 * @author Team 6
 * @version 1.0 11/26/17
 */

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Purpose: Find a user by username.
     * @param username The booking to be added to the calendar.
     * @return Boolean for successful repository save.
     */
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Purpose: Create a new user.
     * @param user The user to be added.
     */
    public void save(User user) {
        userRepository.save(user);
    }
}
