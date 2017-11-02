package com.sealteam6.controllers;

import com.sealteam6.domainmodel.User;
import com.sealteam6.domainmodel.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    UserRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index.html";
    }

}
