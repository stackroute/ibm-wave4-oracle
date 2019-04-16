//package com.stackroute.userservice.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stackroute.userservice.domain.User;
//import com.stackroute.userservice.service.UserServiceImpl;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.when;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest
//public class UserRegistrationControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    private User user;
//    @MockBean
//    private UserServiceImpl userService;
//    @InjectMocks
//    private UserLoginController userLoginController;
//    private List<User> list = null;
//
//    @Before
//    public void setUp() throws Exception {
//
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(userLoginController).build();
//        user = new User();
//        user.setId(1);
//        user.setConfirmPassword("yutr");
//        list = new ArrayList();
//        list.add(user);
//
//    }
//
//    @Test
//    public void registration()throws Exception{
//
//        when(userService.getUserByEmail("priyamd2017@gmail.com")).thenReturn(user);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/saveUser")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//    private static String asJsonString(final Object obj)
//    {
//        try{
//            return new ObjectMapper().writeValueAsString(obj);
//
//        }catch(Exception e){
//            throw new RuntimeException(e);
//        }
//    }
//
//}