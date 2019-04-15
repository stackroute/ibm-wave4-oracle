package com.stackroute.graphqueryservice.listner;

import com.google.gson.Gson;
import com.stackroute.graphqueryservice.domain.QuestionDTO;
import com.stackroute.graphqueryservice.service.GraphQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.boot.json.GsonJsonParser;

@Service
public class ConsumerService {

    private final Logger logger = LoggerFactory.getLogger(ConsumerService.class);
    private GraphQueryService graphQueryService;


    @Autowired
    public ConsumerService(GraphQueryService graphQueryService) {
        this.graphQueryService = graphQueryService;
    }


    @KafkaListener(id = "graphGroup", topics = "updated_query")
    public void listen(QuestionDTO questionDTO) {

        logger.info("Inside Kafka Consumer ******Received: " + questionDTO);
        System.out.println(questionDTO.getAnswer());
        System.out.println(questionDTO.getQuestion());
        System.out.println(questionDTO.getConcept());
        System.out.println(graphQueryService.getClass());


        graphQueryService.createNodesAndRelationships(questionDTO.getConcept(),questionDTO.getQuestion(),questionDTO.getAnswer());

        System.out.println("Relationship created");

    }


//    @KafkaListener(topics = "updated_query", groupId = "graphGroup")
//    public void consume(String message) {
//        JSONObject object = (JSONObject) JSONValue.parse(message);
//        System.out.println(object);
//
//        logger.info("recieved :"+message);
//
//        Gson gson=new Gson();
//        QuestionDTO questionDTO = gson.fromJson(message, QuestionDTO.class);
//        logger.info("questiondto1="+questionDTO.toString());
//        //Converting JsonObject to Paragraph domain object
//
//        logger.info("Inside Kafka Consumer ******Received: " + questionDTO);
//        System.out.println(questionDTO.getAnswer());
//        System.out.println(questionDTO.getQuestion());
//        System.out.println(questionDTO.getConcept());
//        System.out.println(graphQueryService.getClass());
//        graphQueryService.createNodesAndRelationships(questionDTO.getConcept(),questionDTO.getQuestion(),questionDTO.getAnswer());
//
//        System.out.println("Relationship created");
//
//
//    }
}
