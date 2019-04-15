package com.stackroute.manualservice.listener;

import com.google.gson.Gson;
import com.stackroute.manualservice.domain.QuestionDTO;
import com.stackroute.manualservice.service.ManualService;
import com.stackroute.manualservice.service.ManualServiceImpl;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
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
        System.out.println(object);
        logger.info("recieved :"+message);
        Gson gson=new Gson();
        QuestionDTO questionDTO = gson.fromJson(message, QuestionDTO.class);
        logger.info("questiondto1="+questionDTO.toString());
        //Converting JsonObject to Paragraph domain object

        manualService.saveToDataBase(questionDTO);
    }
}
