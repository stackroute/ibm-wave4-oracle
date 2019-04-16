//package com.stackroute.userservice.repo;
//
//import com.stackroute.userservice.domain.User;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest
//public class UserRepositoryTest {
//
//    @Autowired
//    private UserRepository userRepository;
//    private User user;
//
//    @Before
//    public void setUp() throws Exception {
//        user = new User();
//        user.setId(10);
//        user.setFirstName("John");
//        user.setLastName("Jenny");
//
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        userRepository.deleteAll();
//    }
//
//    @Test
//    public void testSaveUser(){
//        userRepository.save(user);
//        User fetchUser = userRepository.findById(user.getId()).get();
//        Assert.assertEquals(101,fetchUser.getId());
//
//    }
//
//    @Test
//    public void testSaveUserFailure(){
//        User testUser = new User();
//        userRepository.save(user);
//        User fetchUser = userRepository.findById(user.getId()).get();
//        Assert.assertNotSame(testUser,user);
//    }
//
//
//
//}