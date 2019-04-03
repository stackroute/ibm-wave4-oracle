package com.stackroute.userservice.service;

import com.stackroute.userservice.model.User;

import java.util.List;

public interface UserService {
    User save(User user);

    List<User> findall();

    User getUserByEmail(String email);
}
