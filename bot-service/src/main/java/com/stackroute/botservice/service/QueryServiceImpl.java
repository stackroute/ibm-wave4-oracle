package com.stackroute.botservice.service;

import com.stackroute.botservice.domain.UserQuery;
import com.stackroute.botservice.repository.QueryRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryServiceImpl implements QueryService {


    private QueryRespository queryRespository;

    @Autowired
    public QueryServiceImpl(QueryRespository queryRespository) {
        this.queryRespository = queryRespository;
    }

    //this method add query to mongo database
    @Override
    public UserQuery saveQuery(UserQuery userQuery) {
        return queryRespository.save(userQuery);
    }

}
