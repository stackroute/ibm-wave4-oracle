//package com.stackroute.botservice.service;
//
//
//import com.stackroute.botservice.domain.Query;
//import com.stackroute.botservice.domain.UserQuery;
//import com.stackroute.botservice.repository.QueryRespository;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.junit.Assert.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.when;
//import static org.junit.Assert.*;
//
//public class QueryServiceImplTest {
//
//
//    private UserQuery userQuery;
//    @Mock
//    private QueryRespository queryRespository;
//
//    @InjectMocks
//    private QueryServiceImpl queryService;
//
//
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        userQuery = new UserQuery();
//        Query query = new Query();
//        userQuery.setQuery(query);
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }
//
//    @Test
//    public void savedatatest() {
//        when(queryRespository.save((UserQuery) any())).thenReturn(userQuery);
//        UserQuery savedUser = queryService.saveQuery(userQuery);
//        Assert.assertEquals(userQuery, savedUser);
//    }
//
//}