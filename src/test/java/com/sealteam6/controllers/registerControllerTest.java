package com.sealteam6.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sealteam6.domainmodel.Customer;
import com.sealteam6.domainmodel.User;
import com.sealteam6.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import java.util.Collections;

@RunWith(SpringRunner.class)
@WebMvcTest(RegisterController.class)
@AutoConfigureMockMvc(secure = false)
public class registerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @Mock
    Model model;

    private RegisterController registerController;

    private String USERNAME = "fakeUser";
    private String EMAIL = "email@mail.com";
    private String PASSWORD = "password";

    @Before
    public void setup() {
        registerController = new RegisterController(authenticationManager, userService);
    }

    
    @Test
    public void registerWithTakenUsername() throws Exception {
        when(userService.findUserByUsername(anyString())).thenReturn(new Customer(USERNAME, PASSWORD, EMAIL));
        registerController.registerUser(USERNAME, PASSWORD, EMAIL, new MockHttpServletRequest(), model);
        verify(userService, times(0)).save(anyObject());
        verify(userService, times(1)).findUserByUsername(anyString());
    }

    @Test
    public void registerWithUntakenUsername() throws Exception {
        when(userService.findUserByUsername(anyString())).thenReturn(null);
        registerController.registerUser(USERNAME, PASSWORD, EMAIL, new MockHttpServletRequest(), model);
        verify(userService, times(1)).save(anyObject());
        verify(userService, times(1)).findUserByUsername(anyString());
    }
}