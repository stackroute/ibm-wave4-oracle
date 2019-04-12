package com.stackroute.manualservice.listener;

import com.stackroute.manualservice.domain.QuestionDTO;
import com.stackroute.manualservice.service.ManualService;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
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
        JSONObject object = (JSONObject) JSONValue.parse(message);
        logger.info(message);
        //Converting JsonObject to Paragraph domain object
        QuestionDTO questionDTO = new QuestionDTO(null,object.get("concept").toString(), object.get("question").toString(), object.get("answer").toString());
        logger.info(questionDTO.toString());
        // these method are similar to the methods present in controller
        manualService.saveToDataBase(questionDTO);
    }
}
