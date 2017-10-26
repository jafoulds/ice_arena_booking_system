package com.team6.controllers;

import lombok.Getter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Getter
public class HomeController {

    @RequestMapping("/")
    public String index() {
        return "hello";
    }
}
