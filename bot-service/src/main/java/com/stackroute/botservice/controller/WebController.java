package com.stackroute.botservice.controller;

import com.stackroute.botservice.domain.LoginRequest;
import com.stackroute.botservice.domain.LoginResponse;
import com.stackroute.botservice.domain.User;
import com.stackroute.botservice.domain.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller

public class WebController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/hello")
    public void loginResponse(Principal principal, LoginRequest loginRequest) throws  Exception {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setContent("Hello  ");

        messagingTemplate.convertAndSendToUser(loginRequest.getToUser(), "/queue/reply", loginResponse);
    }

}
