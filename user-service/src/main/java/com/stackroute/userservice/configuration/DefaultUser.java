package com.stackroute.userservice.configuration;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.service.PasswordUtil;
import com.stackroute.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component
public class DefaultUser implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        User u = new User(1,"admin","admin","admin@mail.com",
                "Admin@123", "Admin@123", true,"ADMIN",null);

        userService.save(u);
    }
}
