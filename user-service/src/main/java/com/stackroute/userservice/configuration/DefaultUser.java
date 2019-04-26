package com.stackroute.userservice.configuration;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component
public class DefaultUser implements ApplicationListener<ContextClosedEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
       User u = new User(1,"admin","admin","admin@mail.com", "Admin@123", "Admin@123", true,"ADMIN",null);
        User u2 = new User(1,"user","user","user@mail.com", "User@123", "User@123", true,"USER",null);

       userService.save(u);
    }
}
