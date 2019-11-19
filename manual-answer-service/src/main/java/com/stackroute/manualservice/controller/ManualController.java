package com.stackroute.manualservice.controller;

import com.stackroute.manualservice.domain.Query;
import com.stackroute.manualservice.domain.QuestionDTO;
import com.stackroute.manualservice.domain.UserQuery;
import com.stackroute.manualservice.exception.QueryNotFoundException;
import com.stackroute.manualservice.listener.ProducerService;
import com.stackroute.manualservice.service.ManualService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value="/api/v1")
@CrossOrigin
public class ManualController {

    //Declaration

    private ManualService manualService;
    private ProducerService producerService;

    private final Logger logger = LoggerFactory.getLogger(ManualController.class);

    @Autowired
    public ManualController(ManualService manualService, ProducerService producerService) {
        this.manualService = manualService;
        this.producerService = producerService;

    }

    // Get  Request for getting all the questions

    @GetMapping("/questions")
    public ResponseEntity<List<UserQuery>> getAllQuestions() {

        List<UserQuery> questionList = manualService.getListOfQuestions();

        return new ResponseEntity<>(questionList, HttpStatus.OK);

    }

    //Get Question by Topic Name

    @GetMapping("/question/{topic}")
    public ResponseEntity<UserQuery>getByTopicName(@PathVariable("topic") String topic) {
        ResponseEntity responseEntity;

        UserQuery queryList = manualService.getQuestionsByTopicName(topic);
        responseEntity = new ResponseEntity<>(queryList, HttpStatus.ACCEPTED);
        return responseEntity;

    }
    //Delete Request

    @PostMapping("/question/{concept}")
    public ResponseEntity<String> updateQuestion(@RequestBody Query query,@PathVariable("concept") String concept) {


            try {

                 manualService.updateQuestion(query,concept);
                // configure QuestionDTo to send to kafka

                QuestionDTO questionDTO = new QuestionDTO();
                questionDTO.setQuestion(query.getQuestion());
                questionDTO.setId(query.getId());
                questionDTO.setAnswer(query.getAnswer());
                questionDTO.setConcept(concept);



                // send data back to the bot service

                producerService.sendTemplate(questionDTO);

                //Delete that question from Consumer side

                manualService.deleteQuestion(query,concept);

            } catch (QueryNotFoundException e) {
                logger.info(e.getMessage());
            }



        return new ResponseEntity<>("Query Deleted Successfully", HttpStatus.CREATED);
    }

}
