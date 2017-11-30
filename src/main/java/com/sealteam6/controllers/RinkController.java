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

/**
 * SENG-330/Fall 2017 - Project Iceman Cometh (Team 6)
 * RinkController.java
 * Purpose: Rink controller class - Create/delete ice rinks in repo.
 *
 * @author Team 6
 * @version 1.0 11/26/17
 */

@RestController
public class RinkController {

    @Autowired
    RinkRepository rinkRepository;

    /**
     * Purpose: Add a new rink.
     * @param number Designator of the rink.
     * @return HTTP status for successful/unsuccessful repository update.
     */
    @RequestMapping("/api/addRink")
    public String addRink(@RequestParam String number) {
        rinkRepository.save(new Rink(number));
        return "added rink";
    }

    /**
     * Purpose: Retrieves all rinks.
     * @return List of all Rink objects.
     */
    @RequestMapping(value = "/api/getRinks", method = RequestMethod.GET)
    public List<Rink> getRinks() {
        return rinkRepository.findAll();
    }

    /**
     * Purpose: Retrieves a rink.
     * @param id Rink id.
     * @return Rink object requested.
     */
    @RequestMapping(value = "/api/getRink", method = RequestMethod.GET)
    public Rink getRink(@RequestParam String id) { return rinkRepository.findOne(id);}
}
