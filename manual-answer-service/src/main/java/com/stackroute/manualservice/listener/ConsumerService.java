package com.stackroute.manualservice.listener;

import com.google.gson.Gson;
import com.stackroute.manualservice.domain.QuestionDTO;
import com.stackroute.manualservice.service.ManualService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class ConsumerService {
    //logging
    private Logger logger= LoggerFactory.getLogger(ConsumerService.class);

    ManualService manualService;

    @Autowired
    public ConsumerService(ManualService manualService) {

        this.manualService = manualService;
    }


    @KafkaListener(topics = "new_query", groupId = "group_id")
    public void consume(String message) {

        logger.info(message);
        Gson gson=new Gson();
        QuestionDTO questionDTO = gson.fromJson(message, QuestionDTO.class);

        //Converting JsonObject to Paragraph domain object
        manualService.saveToDataBase(questionDTO);
    }
}
