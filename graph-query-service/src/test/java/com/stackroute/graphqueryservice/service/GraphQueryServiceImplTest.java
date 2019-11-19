package com.stackroute.graphqueryservice.service;

import com.stackroute.graphqueryservice.domain.Response;
import com.stackroute.graphqueryservice.domain.ResponseDTO;
import com.stackroute.graphqueryservice.repository.ConceptRepository;
import com.stackroute.graphqueryservice.repository.QueryRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GraphQueryServiceImplTest {


    @InjectMocks
    private GraphQueryServiceImpl graphQueryService;

    @Mock
    private QueryRepository queryRepository;

    @Mock
    private ConceptRepository conceptRepository;


    private ResponseDTO responseDTO;
    private List<Response> responseList = null;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
//        responseList = new ArrayList<>();
//        Response response = new Response();
//        response.setQuestion("what is java");
//        response.setAnswer("Answer1");
//
//
//        Response response1 = new Response();
//        response.setQuestion("what is java");
//        response.setAnswer("Answer2");
//
//        responseList.add(response);
//        responseList.add(response1);


    }


    @Test
    public void solution() {
        Assert.assertTrue(queryRepository.findAll() != null);

    }

}