package com.sealteam6.controllers;

import com.sealteam6.domainmodel.User;
import com.sealteam6.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Temporary class for looking at User data
 * in the browser.
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value = "/getCurrentUser", method = RequestMethod.GET)
    public Map<String, String> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Collections.singletonMap("username", authentication.getName());
    }

    @RequestMapping(value = "/userIsLoggedIn", method = RequestMethod.GET)
    public Map<String, Boolean> userIsLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean authenticated = authentication.getName() != "anonymousUser" && authentication.isAuthenticated();
        return Collections.singletonMap("authenticated", authenticated);
    }

}
