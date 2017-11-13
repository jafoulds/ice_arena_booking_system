package com.sealteam6.controllers;

import com.sealteam6.domainmodel.User;
import com.sealteam6.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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


    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
