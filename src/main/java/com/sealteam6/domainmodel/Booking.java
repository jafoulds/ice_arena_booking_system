package com.sealteam6.domainmodel;

import lombok.Getter;
//import java.util.Date;
import org.springframework.data.annotation.Id;

@Getter
public class Booking {

    @Id
    public String id;
    
    protected String startDate;
    protected String endDate;
    protected String rinkID;
    protected String username;
    
    public Booking () {}

    public Booking (String username, String startDate, String endDate, String rinkID){
        this.startDate = startDate;
        this.endDate = endDate;
        this.rinkID = rinkID;
        this.username = username;
    }

    @Override
    public String toString() {
        return String.format(
                "Booking[id=%s, startDate='%s', endDate='%s', rink = '%s', username='%s']",
                id, startDate, endDate, rinkID, username);
    }



}


