package com.stackroute.botservice.controller;


import com.google.gson.Gson;
import com.stackroute.botservice.domain.*;
import com.stackroute.botservice.service.QueryService;
import com.stackroute.botservice.service.SocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* Created on : 27/03/2019 - Gopal Panchal and Subhajit Pal (@rahzex)*/

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class BotController {

    private SimpMessagingTemplate template;
    private final Logger logger= LoggerFactory.getLogger(BotController.class);
    private Gson gson=new Gson();

    private KafkaTemplate<Object, Object> kafkaTemplate;
    private QueryService queryService;
    private SocketService socketService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${autoCorrectorURI}")
    private String AUTO_CORRECTOR_URI;

    @Value("${conceptURI}")
    private String CONCEPT_URI;

    @Value("${neo4jAnsURI}")
    private String NEO4J_ANSWER_URI;

    @Value("${intentURI}")
    private String Intent_URI;

    @Autowired
    public BotController(KafkaTemplate<Object, Object> kafkaTemplate,SocketService socketService,QueryService queryService,SimpMessagingTemplate template) {
        this.kafkaTemplate = kafkaTemplate;
        this.queryService = queryService;
        this.template = template;
        this.socketService = socketService;
    }

    @MessageMapping("/message")
    public void onReceievingMessage(@Header("userName")String userName, String message) throws Exception {
        logger.info(message + " " + userName);
        SendQuery sendQuery = gson.fromJson(message, SendQuery.class);
        logger.info(sendQuery.toString());
        template.convertAndSendToUser(userName, "/reply",gson.toJson(socketService.getAnswer(sendQuery)));
    }


    /*
        This method saves answer/answers if accepted by user else the question will be sent to
        manual answer service.
    */

    @PostMapping("/saveanswer")
    public ResponseEntity<String> saveQueryAnswer(@RequestBody SendQuery sendQuery) {

        String correctedQuery = restTemplate.getForObject(AUTO_CORRECTOR_URI + sendQuery.getQueryAnswer().getQuestion(), String.class).toLowerCase();
        String concept = restTemplate.getForObject(CONCEPT_URI + correctedQuery, String.class);

        ResponseEntity<String> responseEntity = new ResponseEntity<String>("Request Not Supported", HttpStatus.BAD_REQUEST);
        /* if answer is accepted by user then send it to neo4j via kafka and save in mongodb */
        if (sendQuery.getStatus().isAnswered() && sendQuery.getStatus().isAccepted()) {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setConcept(concept);
            questionDTO.setQuestion(correctedQuery);
            questionDTO.setAnswer(sendQuery.getQueryAnswer().getAnswer());
            // saving it in mongodb
            queryService.updateQueryAnswer(concept, correctedQuery, sendQuery.getQueryAnswer().getAnswer());

            kafkaTemplate.send("answerwithquery", questionDTO);
            responseEntity = new ResponseEntity<String>("Sent to Neo4j", HttpStatus.ACCEPTED);
        }
        /* if answer is NOT accepted by user then send it to manual-answer-service via kafka */
        if (!sendQuery.getStatus().isAccepted()) {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setConcept(concept);
            questionDTO.setQuestion(correctedQuery);
            kafkaTemplate.send("new_query", questionDTO);
            responseEntity = new ResponseEntity<String>("Sent to Manual-Answer-Service", HttpStatus.ACCEPTED);
        }

        return responseEntity;
    }
}




