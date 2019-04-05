package com.stackroute.graphquery.service;

import com.stackroute.graphquery.domain.Answer;
import com.stackroute.graphquery.domain.Concept;
import com.stackroute.graphquery.domain.Questions;
import org.springframework.stereotype.Service;

@Service
public interface GraphQueryService {

    /*
        This method takes concept and question through rest call and
        returns the set of answers for that particular question and concept
     */
    Iterable<Questions> solution(String concept);

    /*
        This method displays all set of question and answer
     */
    Iterable<Questions> getAll();

    /*
        This method takes concept,question and answer through rest call and
        creates question and answer domain and also creates relationship between them,
        and also this entire set is attached to particular concept
     */
    Iterable<Questions> createNodesAndRelationships(String concept, String question, String answer);

    /*
        This method displays all the concepts
     */
    Iterable<Concept> concepts();
}
