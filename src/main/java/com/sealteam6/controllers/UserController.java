package com.sealteam6.controllers;

import com.sealteam6.domainmodel.User;
import com.sealteam6.repository.UserDetailsRepository;
import com.sealteam6.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Temporary class for looking at User data
 * in the browser.
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @RequestMapping(name = "/findAll", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @RequestMapping(name = "/details")
    public List<UserDetails> details() {
        return userDetailsRepository.findAll();
    }
}
