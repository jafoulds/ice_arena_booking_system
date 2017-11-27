package com.sealteam6.controllers;

import com.sealteam6.domainmodel.Rink;
import com.sealteam6.repository.RinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RinkController {

    @Autowired
    RinkRepository rinkRepository;


    @RequestMapping("/api/addRink")
    public String addRink(@RequestParam String number) {
        rinkRepository.save(new Rink(number));
        return "added rink";
    }

    @RequestMapping(value = "/api/getRinks", method = RequestMethod.GET)
    public List<Rink> getRinks() {
        return rinkRepository.findAll();
    }

    @RequestMapping(value = "/api/getRink", method = RequestMethod.GET)
    public Rink getRink(@RequestParam String id) { return rinkRepository.findOne(id);}
}
