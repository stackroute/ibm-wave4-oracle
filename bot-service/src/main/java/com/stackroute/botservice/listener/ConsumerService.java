package com.stackroute.botservice.listener;

import com.google.gson.Gson;
import com.stackroute.botservice.domain.QuestionDTO;
import com.stackroute.botservice.service.QueryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class ConsumerService {
    //logging
    private Logger logger= LoggerFactory.getLogger(ConsumerService.class);

    private QueryService queryService;

    @Autowired
    public ConsumerService(QueryService queryService) {
        this.queryService = queryService;
    }

    /* consume method is triggered when an un-answered question have been answered in manual answer service and
    *  that object is produced to be consumed and saved on mongodb for same answer searching*/

    @KafkaListener(topics = "updated_query", groupId = "group_json")
    public void consume(String message) {
        logger.info(message);

        Gson gson=new Gson();
        QuestionDTO questionDTO = gson.fromJson(message, QuestionDTO.class);

        /* adding newly answered question to mongodb */
        queryService.updateQueryAnswer(questionDTO.getConcept(),questionDTO.getQuestion(),questionDTO.getAnswer());
    }
}
