package com.stackroute.botservice.controller;


import com.stackroute.botservice.domain.*;
import com.stackroute.botservice.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/* Created on : 27/03/2019 - Gopal Panchal and Subhajit Pal (@rahzex)*/

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class BotController {

    private KafkaTemplate<Object, Object> kafkaTemplate;
    private QueryService queryService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${autoCorrectorURI}")
    private String AUTO_CORRECTOR_URI;

    @Value("${conceptURI}")
    private String CONCEPT_URI;

    @Value("${neo4jAnsURI}")
    private String NEO4J_ANSWER_URI;

    @Autowired
    public BotController(KafkaTemplate<Object, Object> kafkaTemplate, QueryService queryService) {
        this.kafkaTemplate = kafkaTemplate;
        this.queryService = queryService;

    }

    /*
        This method returns answer/answers for a requested question.
    */

    @PostMapping("/getanswer")
    public ResponseEntity<?> getAnswer(@RequestBody SendQuery sendQuery) {

        String correctedQuery = restTemplate.getForObject(AUTO_CORRECTOR_URI + sendQuery.getQueryAnswer().getQuestion(), String.class).toLowerCase();
        String concept = restTemplate.getForObject(CONCEPT_URI + correctedQuery, String.class);

        System.out.println("Query : " + correctedQuery);
        System.out.println("Concept : " + concept);

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

            Response probableAnswers = restTemplate.getForObject(NEO4J_ANSWER_URI + concept, Response.class);
            List<QueryAnswer> queryAnswer = probableAnswers.getResponses();

            for (QueryAnswer qa : queryAnswer) {
                response.add(new SendQuery(qa, new Status(false, false,true)));
            }
        }
        /* Default answer to client if concept is not found.*/
        if(response.isEmpty()){
            String[] defaultResponses = {"We don't have a answer for this question right now. We will get back to you via email.",
                    "Oops! Seems like we have to figure it out too:( We will mail you once we figure it out:)",
                    "Our Expert Scientist are working on this! We will get back to you via mail.",
                    "Hmmm... looks like I don't know this yet. Let me tell this to my Human master.He will get back to you via mail."};
            int replyIndex = (int) (Math.random() * ((3) + 1));
            response.add(new SendQuery(new QueryAnswer("","",defaultResponses[replyIndex]), new Status(false, false,false)));

            // sending to manual answer service
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setConcept(concept);
            questionDTO.setQuestion(correctedQuery);
            kafkaTemplate.send("new_query", questionDTO);
        }

        return new ResponseEntity<List<SendQuery>>(response, HttpStatus.OK);
    }

    /*
        This method saves answer/answers if accepted by user else the question will be sent to
        manual answer service.
    */

    @PostMapping("/saveanswer")
    public ResponseEntity<?> saveQueryAnswer(@RequestBody SendQuery sendQuery) {

        String correctedQuery = restTemplate.getForObject(AUTO_CORRECTOR_URI + sendQuery.getQueryAnswer().getQuestion(), String.class).toLowerCase();
        String concept = restTemplate.getForObject(CONCEPT_URI + correctedQuery, String.class);

        ResponseEntity<?> responseEntity = new ResponseEntity<>("Request Not Supported", HttpStatus.BAD_REQUEST);
        /* if answer is accepted by user then send it to neo4j via kafka and save in mongodb */
        if (sendQuery.getStatus().isAnswered() && sendQuery.getStatus().isAccepted()) {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setConcept(concept);
            questionDTO.setQuestion(correctedQuery);
            questionDTO.setAnswer(sendQuery.getQueryAnswer().getAnswer());
            // saving it in mongodb
            queryService.updateQueryAnswer(concept, correctedQuery, sendQuery.getQueryAnswer().getAnswer());

            kafkaTemplate.send("answerwithquery", questionDTO);
            responseEntity = new ResponseEntity<>("Sent to Neo4j", HttpStatus.ACCEPTED);
        }
        /* if answer is NOT accepted by user then send it to manual-answer-service via kafka */
        if (!sendQuery.getStatus().isAccepted()) {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setConcept(concept);
            questionDTO.setQuestion(correctedQuery);
            kafkaTemplate.send("new_query", questionDTO);
            responseEntity = new ResponseEntity<>("Sent to Manual-Answer-Service", HttpStatus.ACCEPTED);
        }

        return responseEntity;
    }
}




