package com.stackroute.manualservice.service;

import com.stackroute.manualservice.domain.Query;
import com.stackroute.manualservice.domain.QuestionDTO;
import com.stackroute.manualservice.domain.UserQuery;
import com.stackroute.manualservice.exception.QueryNotFoundException;
import com.stackroute.manualservice.repository.ManualRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManualServiceImpl implements ManualService {

    private ManualRepository manualRepository;

    //Declaration

    private final Logger logger = LoggerFactory.getLogger(ManualServiceImpl.class);

    @Autowired
    public ManualServiceImpl(ManualRepository manualRepository) {

        this.manualRepository = manualRepository;
    }

    // Implemeneting Methods

    //1. Save user met2hod

//    @Override
//    public Query saveQuestion(Query query) {
//        logger.info("Save User:" + query);
//
//        Query saveQuery = manualRepository.save(query);
//        return query;
//
//    }


    //Save to the data base

    public UserQuery saveToDataBase(QuestionDTO questionDTO) {

        UserQuery userQuery = getQuestionsByTopicName(questionDTO.getConcept());
        Query query = new Query();
        query.setId(questionDTO.getId());
        query.setQuestion(questionDTO.getQuestion());

        //if no Document with concept is present
        if (userQuery == null){
        List<Query> queryList = new ArrayList<Query>();
        queryList.add(query);
        userQuery.setQuery(queryList);
         return manualRepository.save(userQuery);
        }

        //if document with the DTO is Present

        else {
            List<Query> queryList = userQuery.getQuery();
            queryList.add(query);
            userQuery.setQuery(queryList);
            return manualRepository.save(userQuery);

        }

    }

    // 2. Get list of questions
    @Override
    public List<UserQuery> getListOfQuestions() {

        List<UserQuery> userQueryList = (List<UserQuery>) manualRepository.findAll();

        return userQueryList;
    }

    //3. Update Question

    @Override
    public UserQuery updateQuestion(Query query,String concept) throws QueryNotFoundException {

        UserQuery userQuery = getQuestionsByTopicName(concept);

        //if user query is null

        if(userQuery != null){
            boolean isQuestionFound = false;
            List<Query> queryList = userQuery.getQuery();
            for (Query item : queryList) {
                if(item.getId()  == query.getId()){
                    item.setAnswer(query.getAnswer());
                    isQuestionFound = true;
                }
            }

            //check is question found or not
            if(isQuestionFound){
                userQuery.setQuery(queryList);
               return manualRepository.save(userQuery);
            }
            else {
                throw new QueryNotFoundException("Question not found with this id");
            }

        }

        // if it is not null

        else {
            throw new QueryNotFoundException("User Query not found for this concept");
        }

    }

    // 4. Delete The user Question

    @Override
    public UserQuery deleteQuestion(Query query,String concept) throws QueryNotFoundException{

        UserQuery userQuery = getQuestionsByTopicName(concept);

        //if user query is null

        if(userQuery != null){
            boolean isQuestionFound = false;
            List<Query> queryList = userQuery.getQuery();
            for (Query item : queryList) {
                if(item.getId()  == query.getId()){
                    item.setAnswer(query.getQuestion());
                    isQuestionFound = true;
                }
            }

            //check is question found or not
            if(isQuestionFound){
                queryList.remove(query);
                userQuery.setQuery(queryList);
               return manualRepository.save(userQuery);
            }
            else {
                throw new QueryNotFoundException("Question not found with this id");
            }

        }

        // if it is not null

        else {
            throw new QueryNotFoundException("User Query not found for this concept");
        }
    }

    //5.Get Query by Topic name

    @Override
    public UserQuery getQuestionsByTopicName(String topic_name) {

        UserQuery queryList = (UserQuery) manualRepository.searchByTopicName(topic_name);
        return queryList;
    }

}
