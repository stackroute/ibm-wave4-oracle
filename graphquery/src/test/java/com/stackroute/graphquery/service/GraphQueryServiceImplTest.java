package com.stackroute.graphquery.service;

import com.stackroute.graphquery.domain.Answer;
import com.stackroute.graphquery.domain.Concept;
import com.stackroute.graphquery.domain.Questions;
import com.stackroute.graphquery.repository.ConceptRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class GraphQueryServiceImplTest {

  /*  private Answer answer;
    private Questions questions;
    private Concept concept;
    @Mock
    private ConceptRepository conceptRepository;

    @InjectMocks
    private GraphQueryServiceImpl graphQueryService;
*/
    @Before
    public void setUp() throws Exception {
     /*   MockitoAnnotations.initMocks(this);

        questions.setName("commands");
        questions.setConcept("Mongo");
        answer.setAnswer("solution");
        answer.setConcept("Mongo");
        concept.setName("Mongo");*/

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void solution() {

    }

    @Test
    public void getAll() {

    }

    @Test
    public void createNodesAndRelationships() {
    }

    @Test
    public void concepts() {
      /* when(conceptRepository.findAll()).thenReturn((Iterable<Concept>) concept);
        Concept concept1=new Concept();
        concept1.setName("Mongo");
        Assert.assertEquals(concept, concept1);*/
    }
}