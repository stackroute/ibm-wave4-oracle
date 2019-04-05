package com.stackroute.graphquery.domain;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public class AnswerTest {

    Answer answer=new Answer();
    @Before
    public void setUp() throws Exception {
        answer.setConcept("Mongo");
        answer.setAnswer("solution");
    }

    @After
    public void tearDown() throws Exception {
    }
}