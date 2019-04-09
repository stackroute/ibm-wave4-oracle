package com.stackroute.manualservice.service;

import com.stackroute.manualservice.controller.ManualController;
import com.stackroute.manualservice.domain.Query;
import com.stackroute.manualservice.domain.QuestionDTO;
import com.stackroute.manualservice.domain.UserQuery;
import com.stackroute.manualservice.repository.ManualRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.mockito.Mockito.*;

public class ManualServiceTests {


    private Query query;
    private UserQuery userQuery;

    private List<Query> queryList = null;
    @Mock
    private ManualRepository manualRepository;

    @InjectMocks
    private ManualServiceImpl manualService;


    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        query = new Query();
        query.setQuestion("abcd");
        query.setAnswer("kjsfdjefj");
        queryList.add(query);
        userQuery = new UserQuery();
        userQuery.setQuery(queryList);
        userQuery.setConcept("concept");
        userQuery.setId("we");
    }

    @Test
    public void testSaveQuestionSuccess(){


        when(manualRepository.save((UserQuery) ArgumentMatchers.any())).thenReturn(userQuery);
        UserQuery savedQuery = manualService.saveToDataBase(new QuestionDTO("1","concept","abcd","kjsfdjefj"));
        Assert.assertEquals(userQuery,savedQuery);

        // Here we are verifying that userrepository save method is only called once
        verify(manualRepository,times(1)).save(new UserQuery());
    }

}
