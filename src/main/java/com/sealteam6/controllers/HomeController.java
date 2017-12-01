package com.sealteam6.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * SENG-330/Fall 2017 - Project Iceman Cometh (Team 6)
 * HomeController.java
 * Purpose: Home controller class - Frontpage request handler.
 *
 * @author Team 6
 * @version 1.0 11/26/17
 */

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index";
    }

}
