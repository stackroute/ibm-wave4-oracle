package com.stackroute.userservice.controller;


import com.stackroute.userservice.domain.Response;
import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/saveUser")
    public ResponseEntity<?> registration(@RequestBody User user)
    {
              User user1 = userService.save(user);
              if (user1!=null)
              {
                  return new ResponseEntity<>(new Response("user is successfully added"), HttpStatus.OK);
              }
              return null;
    }
}
