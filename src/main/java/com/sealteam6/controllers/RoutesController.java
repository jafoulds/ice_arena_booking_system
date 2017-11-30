package com.sealteam6.controllers;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SENG-330/Fall 2017 - Project Iceman Cometh (Team 6)
 * RoutesController.java
 * Purpose: Routes controller class - request-to-uri path error mapping.
 *
 * @author Team 6
 * @version 1.0 11/26/17
 */

@Controller
public class RoutesController implements ErrorController {
    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "index";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}