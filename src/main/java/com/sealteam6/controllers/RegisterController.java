package com.sealteam6.controllers;

import com.sealteam6.domainmodel.Customer;
import com.sealteam6.domainmodel.User;
import com.sealteam6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private UserService userService;

    private AuthenticationManager authenticationManager;

    @Autowired
    public RegisterController(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registerUser(
            @RequestParam String username, @RequestParam String password, @RequestParam String emailAddress,
            HttpServletRequest request, Model model) {

        User user = new Customer(username, password, emailAddress);
        if (userService.findUserByUsername(username) != null) {
            model.addAttribute("takenUsername", true);
            return "register";
        }
        userService.save(user);

        authenticateUserAndSetSession(user, request);
        return "redirect:/";
    }

    private void authenticateUserAndSetSession(User user, HttpServletRequest request) {
        String username = user.getUsername();
        String password = user.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        // generate session if one doesn't exist
        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }
}
