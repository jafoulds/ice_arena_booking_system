package com.sealteam6.controllers;

import com.sealteam6.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.Map;

/**
 * SENG-330/Fall 2017 - Project Iceman Cometh (Team 6)
 * LoginController.java
 * Purpose: User controller class -  Temporary class for
 * looking at User data in the browser.
 *
 * @author Team 6
 * @version 1.0 11/26/17
 */

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Purpose: Retrieves current logged-in user.
     * @return Current user's username.
     */
    @RequestMapping(value = "/getCurrentUser", method = RequestMethod.GET)
    public Map<String, String> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Collections.singletonMap("username", authentication.getName());
    }

    /**
     * Purpose: Checks if current user is logged-in.
     * @return Authentication mapping.
     */
    @RequestMapping(value = "/userIsLoggedIn", method = RequestMethod.GET)
    public Map<String, Boolean> userIsLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean authenticated = authentication.getName() != "anonymousUser" && authentication.isAuthenticated();
        return Collections.singletonMap("authenticated", authenticated);
    }

}
