package com.sealteam6.domainmodel;

//import java.util.Date;

public class GroupBooking extends Booking {
	
	protected String grpID;
	
    GroupBooking (String username, String grpID, String startDate, String endDate, String rinkID){
    		super(username, startDate, endDate, rinkID);
        this.grpID = grpID;
    }
	
    @Override
    public String toString() {
        return String.format(
                "Booking[id=%s, startDate='%s', endDate='%s', rink = '%s', username='%s']",
                id, startDate, endDate, rinkID, username);
    }
    
}