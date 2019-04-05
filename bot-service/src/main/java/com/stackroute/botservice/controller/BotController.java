package com.stackroute.botservice.controller;


import com.stackroute.botservice.domain.Query;
import com.stackroute.botservice.domain.SendQuery;
import com.stackroute.botservice.domain.UserQuery;
import com.stackroute.botservice.service.QueryServiceImpl;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/* Created on : 27/03/2019 by gopal */

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class BotController {

    KafkaTemplate<Object, Object> kafkaTemplate;
    private QueryServiceImpl queryService;

    public BotController() {
    }

    @Autowired
    public BotController(KafkaTemplate<Object, Object> kafkaTemplate, QueryServiceImpl queryService) {
        this.kafkaTemplate = kafkaTemplate;
        this.queryService = queryService;
    }

    @PostMapping("/send/query")
    public ResponseEntity<?> sendNewQuery(@RequestBody SendQuery sendQuery) {

        String question= sendQuery.getQuery().getQuestion();
        RestTemplate restTemplate = new RestTemplate();
       // String correctedQuery = restTemplate.getForObject("http://localhost:8595/api/v1/getCorrectedQuery/" + question, String.class);


        String concepts = restTemplate.getForObject("http://localhost:8383/api/v1/concepts/" + question, String.class);

        List<Query> solution = restTemplate.getForObject("http://localhost:8082/api/v1/answer/" + concepts , List.class);


        UserQuery userQuery= new UserQuery();

        userQuery.setConcept(concepts);

        System.out.println(concepts);

        return new ResponseEntity<UserQuery>(userQuery, HttpStatus.CREATED);

    }
}

// Saving it in mongodb

//userQuery = queryService.saveQuery(userQuery);

// Sending it to manual-answer service in case not answered
// kafkaTemplate.send("new_query", userQuery.getQuery());

// Default answer for now
