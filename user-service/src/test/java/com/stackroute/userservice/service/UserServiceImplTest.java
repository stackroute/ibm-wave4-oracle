//package com.stackroute.userservice.service;
//
//import com.stackroute.userservice.domain.User;
//import com.stackroute.userservice.repo.UserRepository;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//import static org.junit.Assert.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyList;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.mockito.internal.verification.VerificationModeFactory.times;
//
//public class UserServiceImplTest {
//    private User user;
//    @Mock
//    private UserRepository userRepository;
//    @InjectMocks
//    private UserServiceImpl userService;
//    private ArrayList list;
//
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        user = new User();
//        user.setId(1);
//        user.setConfirmPassword("yutr");
//        list = new ArrayList();
//        list.add(user);
//    }
//
//    @Test
//    public void save()  throws Exception{
//        when(userRepository.save(user));
//        User saveQuery = userService.save(user);
//        Assert.assertEquals(user, saveQuery);
//
//        //verify here verifies that userRepository save method is only called once
//        verify(userRepository, times(1)).save(user);
//
//    }
//
//    @Test
//    public void findall() {
//        userRepository.save(user);
//        //  stubbing the mock to return specific data
//        when(userRepository.findAll()).thenReturn(anyList());
//        List<User> userQueryList = userService. findall();
//        Assert.assertEquals(anyList(), userQueryList);
//
//    }
//
//    @Test
//    public void getUserByEmail() {
//
//        when(userRepository.findByEmailIgnoreCase("putr")).thenReturn(user);
//        User foundemail = userService.getUserByEmail("abcd");
//        Assert.assertEquals(user,foundemail);
//    }
//
//    }
