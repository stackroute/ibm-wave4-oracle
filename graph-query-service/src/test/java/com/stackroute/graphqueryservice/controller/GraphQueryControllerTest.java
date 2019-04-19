
package com.stackroute.graphqueryservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.graphqueryservice.domain.Concept;
import com.stackroute.graphqueryservice.domain.ResponseDTO;

import com.stackroute.graphqueryservice.service.GraphQueryService;
import org.junit.After;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.data.neo4j.AutoConfigureDataNeo4j;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;



import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
@AutoConfigureDataNeo4j
public class GraphQueryControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    GraphQueryService graphQueryService;

    @InjectMocks
    GraphQueryController graphQueryController;

    private List<Concept> concepts = null;
    private List<ResponseDTO> responseDTOS = null;


    @Before
    public void setUp() throws Exception {

        concepts=new ArrayList<>();
        Concept concept = new Concept();
        concept.setGraphId((long) 1);
        concept.setName("java");

        Concept concept1 = new Concept();
        concept.setGraphId((long) 2);
        concept.setName("docker");

        concepts.add(concept);
        concepts.add(concept1);
    }

    @After
    public void tearDown() throws Exception {
    }


    // Test Cases
    @Test
    public void getConcepts() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/concepts")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(concepts)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
