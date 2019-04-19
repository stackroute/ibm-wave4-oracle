package com.stackroute.graphqueryservice.listner;

import com.google.gson.Gson;
import com.stackroute.graphqueryservice.domain.QuestionDTO;
import com.stackroute.graphqueryservice.service.GraphQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

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
        logger.info(message);

        Gson gson=new Gson();
        QuestionDTO questionDTO = gson.fromJson(message, QuestionDTO.class);

        //Converting JsonObject to Paragraph domain object

        graphQueryService.createNodesAndRelationships(questionDTO.getConcept(),questionDTO.getQuestion(),questionDTO.getAnswer());

        logger.info("Relationship created");


    }
}
