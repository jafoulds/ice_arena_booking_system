package com.sealteam6.controllers;

import com.sealteam6.domainmodel.Booking;
import com.sealteam6.domainmodel.GroupBooking;
import com.sealteam6.domainmodel.Rink;
import com.sealteam6.service.BookingService;
import com.sealteam6.repository.BookingRepository;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//import java.util.Date;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/booking")
public class BookingController {

	private BookingService bookingService;

	@Autowired
	public BookingController (BookingService bookingService) {
		this.bookingService = bookingService;
	}

	@RequestMapping(method = RequestMethod.POST)
  public String addBooking (
    @RequestParam String username,
		@RequestParam String startDate,
		@RequestParam String endDate,
		@RequestParam String rinkID,
		HttpServletRequest request,
    Model model) {

    			Booking booking = new Booking(username, startDate, endDate, rinkID);

    			// Check for incompatible booking
    			//if (bookingService.findBookingsByUsername(username) != null) {
			//	model.addAttribute("takenUsername", true);
			//	return "booking";
			//}
    			bookingService.save(booking);
    			return booking.toString();
    		}

}
