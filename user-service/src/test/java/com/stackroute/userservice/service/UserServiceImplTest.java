
package com.stackroute.userservice.service;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.repo.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
@RunWith(SpringRunner.class)
public class UserServiceImplTest {
    private User user;
    @MockBean
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;


    private ArrayList list;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setId(1);
        user.setConfirmPassword("yutr");
        list = new ArrayList();
        list.add(user);
    }


    @Test
    public void findall() {
        userRepository.save(user);
        //  stubbing the mock to return specific data
        when(userRepository.findAll()).thenReturn(list);
        List<User> userQueryList = userService.findall();
        Assert.assertEquals(list, userQueryList);

    }
}

