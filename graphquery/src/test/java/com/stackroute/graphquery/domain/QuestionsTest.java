package com.stackroute.graphquery.domain;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public class QuestionsTest {

    Questions questions=new Questions();
    @Before
    public void setUp() throws Exception {
        questions.setConcept("Mongo");
        questions.setName("commands");
    }

    @After
    public void tearDown() throws Exception {
    }
}