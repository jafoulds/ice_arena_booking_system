package com.sealteam6.controllers;

import com.sealteam6.domainmodel.User;
import com.sealteam6.repository.UserDetailsRepository;
import com.sealteam6.repository.UserRepository;
import com.sealteam6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Temporary class for looking at User data
 * in the browser.
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public List<UserDetails> details() {
        return userDetailsRepository.findAll();
    }
}
