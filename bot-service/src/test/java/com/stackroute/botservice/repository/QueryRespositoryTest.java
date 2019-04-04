package com.stackroute.botservice.repository;

import com.stackroute.botservice.domain.Query;
import com.stackroute.botservice.domain.UserQuery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class QueryRespositoryTest {


    @Autowired
    private QueryRespository queryRespository;
    private UserQuery userQuery;
    private Query query;

    @Before
    public void setUp() {
        query.setId("101");

    }

    @After
    public void tearDown() {

        queryRespository.deleteAll();
    }


    @Test
    public void testSaveUser() {
        queryRespository.save(userQuery);
        UserQuery fetchUser = queryRespository.findById(userQuery.getQuery().getId()).get();
        Assert.assertEquals("101", fetchUser.getQuery().getId());

    }

    @Test
    public void testSaveUserFailure() {
        UserQuery testUser = new UserQuery();
        queryRespository.save(testUser);
        UserQuery fetchUser = queryRespository.findById(userQuery.getQuery().getId()).get();
        Assert.assertNotSame(testUser, fetchUser);
    }

}