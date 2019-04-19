package com.stackroute.graphqueryservice.service;

import com.stackroute.graphqueryservice.domain.Concept;
import com.stackroute.graphqueryservice.domain.ResponseDTO;
import org.springframework.stereotype.Service;


@Service
public interface GraphQueryService {

    /*
        This method takes concept and question through rest call and
        returns the set of answers for that particular question and concept
     */
    ResponseDTO solution(String concept,String intent);

    /*
        This method takes concept,question and answer through rest call and
        creates question and answer domain and also creates relationship between them,
        and also this entire set is attached to particular concept
     */
    void createNodesAndRelationships(String concept, String question, String answer);

    /*
        This method displays all the concepts
     */
    Iterable<Concept> concepts();
}
