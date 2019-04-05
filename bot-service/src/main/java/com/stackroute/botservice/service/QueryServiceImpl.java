package com.stackroute.botservice.service;

import com.stackroute.botservice.domain.QueryAnsListWithConcept;
import com.stackroute.botservice.repository.QueryRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryServiceImpl implements QueryService {


    private QueryRespository queryRespository;

    @Autowired
    public QueryServiceImpl(QueryRespository queryRespository) {
        this.queryRespository = queryRespository;
    }

    //this method add queryAnswer to mongo database
    @Override
    public QueryAnsListWithConcept saveQuery(QueryAnsListWithConcept queryAnsListWithConcept) {
        return queryRespository.save(queryAnsListWithConcept);
    }

    public List<QueryAnsListWithConcept> getAll(){
        return queryRespository.findAll();
    }

}
