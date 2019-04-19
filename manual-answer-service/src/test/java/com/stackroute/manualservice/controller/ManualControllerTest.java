package com.stackroute.manualservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.manualservice.domain.Query;
import com.stackroute.manualservice.domain.UserQuery;
import com.stackroute.manualservice.listener.ProducerService;
import com.stackroute.manualservice.service.ManualService;
import com.stackroute.manualservice.service.ManualServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ManualControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private UserQuery userQuery;
    private Query query;

    @MockBean
    private ManualService manualService;
    @MockBean
    private ProducerService producerService;

    @InjectMocks
    private ManualController manualController;

    private List<UserQuery> list = null;
    private List<Query> queryList = null;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(manualController).build();
        queryList=new ArrayList<>();
        query = new Query();
        query.setQuestion("Question");
        query.setId("1");
        query.setAnswer("Answer");
        queryList.add(query);
        userQuery = new UserQuery();
        userQuery.setQuery(queryList);
        userQuery.setConcept("concept");
        userQuery.setId("we");

    }

    @Test
    public void getAllQuestions() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/questions")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(userQuery)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }



    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

}