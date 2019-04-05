package com.stackroute.manualservice.controller;

import com.stackroute.manualservice.domain.Query;
import com.stackroute.manualservice.exception.QueryNotFoundException;
import com.stackroute.manualservice.service.ManualService;
import com.stackroute.manualservice.service.ManualServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class ManualController {

    //Declaration

    private ManualService manualService;
    private KafkaTemplate<Object, Object> kafkaTemplate;

    private final Logger logger = LoggerFactory.getLogger(ManualController.class);

    @Autowired
    public ManualController(ManualService manualService, KafkaTemplate<Object, Object> kafkaTemplate) {
        this.manualService = manualService;
        this.kafkaTemplate = kafkaTemplate;
    }

    // Get  Request for getting all the questions

    @GetMapping("/questions")
    public ResponseEntity<List<Query>> getAllQuestions() {

        List<Query> questionList = manualService.getListOfQuestions();

        return new ResponseEntity<List<Query>>(questionList, HttpStatus.OK);

    }

    //Get Question by Topic Name

    @GetMapping("/question/{topic_name}")
    public ResponseEntity<List<Query>> getByTopicName(@PathVariable("topic_name") String topic_name) throws QueryNotFoundException {
        ResponseEntity responseEntity;

        List<Query> queryList = manualService.getQuestionsByTopicName(topic_name);
        responseEntity = new ResponseEntity<List<Query>>(queryList, HttpStatus.ACCEPTED);
        return responseEntity;

    }
    //Delete Request

    @PostMapping("/question")
    public ResponseEntity<String> updateQuestion(@RequestBody Query query) throws QueryNotFoundException {

        Query updateQuestion = manualService.updateQuestion(query);

        logger.info("Updated Questions:" + updateQuestion);

        // send data back to the bot service
        kafkaTemplate.send("update_query", updateQuestion);

        //Delete that quedstion from Consumer side
        manualService.deleteQuestion(query.getId());

        return new ResponseEntity<String >("Quey Deleted Successfully", HttpStatus.CREATED);
    }

}
