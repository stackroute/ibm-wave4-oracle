//package com.stackroute.graphqueryservice.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stackroute.graphqueryservice.domain.Concept;
//import com.stackroute.graphqueryservice.domain.ResponseDTO;
//import com.stackroute.graphqueryservice.service.GraphQueryService;
//import com.stackroute.graphqueryservice.service.GraphQueryServiceImpl;
//import org.junit.After;
//import org.junit.Assert;
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
//
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest
//public class GraphQueryControllerTest {
//
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private GraphQueryService graphQueryService;
//
//    @InjectMocks
//    private GraphQueryController graphQueryController;
//
//    private List<Concept> concepts = null;
//    private List<ResponseDTO> responseDTOS = null;
//
//
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(graphQueryController).build();
//
//        Concept concept = new Concept();
//        concept.setGraphId((long) 1);
//        concept.setName("java");
//
//        Concept concept1 = new Concept();
//        concept.setGraphId((long) 2);
//        concept.setName("docker");
//
//        concepts.add(concept);
//        concepts.add(concept1);
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }
//
//
//    // Test Cases
//    @Test
//    public void getConcepts() throws Exception {
//
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/concepts")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(concepts)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    private static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//}
