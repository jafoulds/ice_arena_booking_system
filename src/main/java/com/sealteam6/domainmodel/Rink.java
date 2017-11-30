package com.sealteam6.domainmodel;

import lombok.Getter;
import lombok.Value;
import org.springframework.data.annotation.Id;

/**
 * SENG-330/Fall 2017 - Project Iceman Cometh (Team 6)
 * Customer.java
 * Purpose: Rink object class - Ice rink that can be booked by a user.
 *
 * @author Team 6
 * @version 1.0 11/26/17
 */

@Getter
@Value
public class Rink {

    @Id
    String id;

}
