package com.stackroute.graphquery.domain;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public class ConceptTest {

    Concept concept=new Concept();
    @Before
    public void setUp() throws Exception {
        concept.setName("Mongo");
    }

    @After
    public void tearDown() throws Exception {
    }
}