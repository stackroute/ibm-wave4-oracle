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




    @KafkaListener(topics = "updated_query", groupId = "graphGroup")
    public void consume(String message) {
        JSONObject object = (JSONObject) JSONValue.parse(message);
       logger.info("info" + object);

        logger.info("recieved :"+message);

        Gson gson=new Gson();
        QuestionDTO questionDTO = gson.fromJson(message, QuestionDTO.class);
        logger.info("questiondto1="+questionDTO);
        //Converting JsonObject to Paragraph domain object

        logger.info("Inside Kafka Consumer Received: " + questionDTO);

        graphQueryService.createNodesAndRelationships(questionDTO.getConcept(),questionDTO.getQuestion(),questionDTO.getAnswer());

        logger.info("Relationship created");


    }
}
