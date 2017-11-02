package com.sealteam6.controllers;

import com.sealteam6.domainmodel.User;
import com.sealteam6.domainmodel.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository repository;

    @RequestMapping(method = RequestMethod.POST)
    public User user(@RequestBody User user) {
        repository.save(user);
        return repository.findByUsername(user.username);
    }

    @RequestMapping(method = RequestMethod.GET)
    public User user(@RequestParam String username) {
        return repository.findByUsername(username);
    }
}
