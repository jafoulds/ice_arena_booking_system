package com.sealteam6.controllers;

import com.sealteam6.domainmodel.User;
import com.sealteam6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public String registerUser(@ModelAttribute User user) {
        user.addRole("ROLE_USER");
        userService.save(user);
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getRegisterPage() {
        return "register";
    }
}
