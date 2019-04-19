//package com.stackroute.botservice.repository;
//
//import com.stackroute.botservice.domain.QueryAnswer;
//import com.stackroute.botservice.domain.QueryAnsListWithConcept;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringRunner.class)
//@DataMongoTest
//public class QueryRespositoryTest {
//
//
//    @Autowired
//    private QueryRespository queryRespository;
//    private QueryAnsListWithConcept userQuery;
//    private QueryAnswer queryAnswer;
//
//    @Before
//    public void setUp() {
//        queryAnswer.setId("101");
//
//    }
//
//    @After
//    public void tearDown() {
//
//        queryRespository.deleteAll();
//    }
//
//
//    @Test
//    public void testSaveUser() {
//        QueryAnsListWithConcept actualvalue=   queryRespository.save(userQuery);
//        QueryAnsListWithConcept expectedvalue=   queryRespository.save(userQuery);
//        Assert.assertEquals(actualvalue, expectedvalue);
//
//    }
//
//    @Test
//    public void testSaveUserFailure() {
//        QueryAnsListWithConcept testUser = new QueryAnsListWithConcept();
//        queryRespository.save(testUser);
//        Assert.assertEquals(testUser, testUser);
//    }
//
//}