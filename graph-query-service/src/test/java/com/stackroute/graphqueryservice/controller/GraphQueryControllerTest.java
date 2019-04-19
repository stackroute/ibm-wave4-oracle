
package com.stackroute.graphqueryservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.graphqueryservice.domain.Concept;
import com.stackroute.graphqueryservice.domain.ResponseDTO;

import org.junit.After;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;



import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GraphQueryControllerTest {


    @Autowired
    private MockMvc mockMvc;

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
