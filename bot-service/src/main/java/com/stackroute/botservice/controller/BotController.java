package com.stackroute.botservice.controller;


import com.stackroute.botservice.domain.*;
import com.stackroute.botservice.service.QueryService;
import com.stackroute.botservice.service.QueryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/* Created on : 27/03/2019 by gopal */

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class BotController {

    private KafkaTemplate<Object, Object> kafkaTemplate;
    private QueryService queryService;

    @Autowired
    public BotController(KafkaTemplate<Object, Object> kafkaTemplate, QueryService queryService) {
        this.kafkaTemplate = kafkaTemplate;
        this.queryService = queryService;

    }

    @PostMapping("/send")
    public ResponseEntity<?> sendNewQuery(@RequestBody SendQuery sendQuery) {
        String question = sendQuery.getQueryAnswer().getQuestion();
        RestTemplate restTemplate = new RestTemplate();
        String correctedQuery = restTemplate.getForObject("http://localhost:8595/api/v1/getCorrectedQuery/" + question, String.class);
        String concepts = restTemplate.getForObject("http://localhost:8383/api/v1/concepts/" + correctedQuery, String.class);

        System.out.println("Query : "+correctedQuery);
        System.out.println("Concept : "+concepts);

        List<SendQuery> response=new ArrayList<>();
        String answer = queryService.getAnswerOfSimilarQuestion(concepts, correctedQuery);
        if (answer != null) {
            sendQuery.setQueryAnswer(new QueryAnswer("",question,answer));
            sendQuery.getStatus().setAnswered(true);
            response.add(sendQuery);
        }
        if (answer == null){
            List<QueryAnswer> solution = restTemplate.getForObject("http://localhost:8082/api/v1/answer/" + concepts, List.class);

            for(QueryAnswer query:solution){
                response.add(new SendQuery(query,new Status(false,true)));
            }
        }
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setConcept(concepts);
        questionDTO.setQuestion(correctedQuery);
        kafkaTemplate.send("new_query", questionDTO);
        System.out.println("===================="+questionDTO);
        //responseEntity = new ResponseEntity<String>("Sent to Manual Answer Service",HttpStatus.CREATED);
        return new ResponseEntity<List<SendQuery>>(response,HttpStatus.OK);
    }
}




