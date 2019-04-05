package com.stackroute.botservice.controller;


import com.mongodb.util.JSON;
import com.stackroute.botservice.domain.Query;
import com.stackroute.botservice.domain.UserQuery;
import com.stackroute.botservice.service.QueryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
    public ResponseEntity<?> sendNewQuery(@RequestBody UserQuery userQuery) {
        // Getting the query from UserQuery object
        Query questionQuery = userQuery.getQuery();

        // Calling query auto-correcter service to correct spelling mistakes
        RestTemplate restTemplate = new RestTemplate();
        String correctedQuery = restTemplate.getForObject("http://localhost:8595/api/v1/getCorrectedQuery/" + questionQuery.getQuestion(), String.class);

        //call to intent -extarct- service

        String concepts = restTemplate.getForObject("http://localhost:8383/api/v1/concepts/" + questionQuery.getQuestion(), String.class);

        // Modifying the query field with corrected query
        System.out.println(concepts);
        questionQuery.setConcept(concepts);
        questionQuery.setQuestion(correctedQuery);
        userQuery.setQuery(questionQuery);

        // Saving it in mongodb
        userQuery = queryService.saveQuery(userQuery);

        // Sending it to manual-answer service in case not answered
       // kafkaTemplate.send("new_query", userQuery.getQuery());

        // Default answer for now
        userQuery.getStatus().setAnswered(true);
        //userQuery.getQuery().setAnswer("I will tell you later or ask Aman Patla");

        return new ResponseEntity<UserQuery>(userQuery, HttpStatus.CREATED);

    }
}
