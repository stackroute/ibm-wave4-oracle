package com.stackroute.botservice.service;

import com.google.gson.Gson;
import com.stackroute.botservice.controller.BotController;
import com.stackroute.botservice.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SocketService {

    private SimpMessagingTemplate template;
    private final Logger logger= LoggerFactory.getLogger(BotController.class);
    private Gson gson=new Gson();
    private KafkaTemplate<Object, Object> kafkaTemplate;
    private QueryService queryService;

    @Autowired
    public SocketService(SimpMessagingTemplate template, KafkaTemplate<Object, Object> kafkaTemplate, QueryService queryService) {
        this.template = template;
        this.kafkaTemplate = kafkaTemplate;
        this.queryService = queryService;
        restTemplate=new RestTemplate();
    }


    private RestTemplate restTemplate;

    @Value("${autoCorrectorURI}")
    private String AUTO_CORRECTOR_URI;

    @Value("${conceptURI}")
    private String CONCEPT_URI;

    @Value("${neo4jAnsURI}")
    private String NEO4J_ANSWER_URI;

    @Value("${intentURI}")
    private String Intent_URI;


    public List<SendQuery> getAnswer(SendQuery sendQuery) {

        String correctedQuery = restTemplate.getForObject(AUTO_CORRECTOR_URI + sendQuery.getQueryAnswer().getQuestion(), String.class).toLowerCase();
        String concept = restTemplate.getForObject(CONCEPT_URI + correctedQuery, String.class);
        String intent = restTemplate.getForObject(Intent_URI + correctedQuery, String.class);
        logger.info(correctedQuery);
        logger.info(concept);

        List<SendQuery> response = null;

        String answer = queryService.getAnswerOfSimilarQuestion(concept, correctedQuery.toLowerCase());
        if (answer != null) {
            response = new ArrayList<>();
            sendQuery.setQueryAnswer(new QueryAnswer("", correctedQuery, answer));
            sendQuery.getStatus().setAnswered(true);
            sendQuery.getStatus().setSuggested(false);
            response.add(sendQuery);
        }
        if (answer == null) {
            response = new ArrayList<>();

            Response probableAnswers = restTemplate.getForObject(NEO4J_ANSWER_URI + concept +"/"+ intent, Response.class);
            List<QueryAnswer> queryAnswer = probableAnswers.getResponses();

            for (QueryAnswer qa : queryAnswer) {
                response.add(new SendQuery(qa, new Status(false, false,true)));
            }
        }
        //* Default answer to client if concept is not found.*//*
        if(response.isEmpty()){
            String[] defaultResponses = {"We don't have a answer for this question right now. We will get back to you via email.",
                    "Oops! Seems like we have to figure it out too:( We will mail you once we figure it out:)",
                    "Our Expert Scientist are working on this! We will get back to you via mail.",
                    "Hmmm... looks like I don't know this yet. Let me tell this to my Human master.He will get back to you via mail."};

            int replyIndex = new Random().nextInt(4);
            response.add(new SendQuery(new QueryAnswer("","",defaultResponses[replyIndex]), new Status(false, false,false)));

            // sending to manual answer service
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setConcept(concept);
            questionDTO.setQuestion(correctedQuery);
            kafkaTemplate.send("new_query", questionDTO);
        }

        return response;
    }

}
