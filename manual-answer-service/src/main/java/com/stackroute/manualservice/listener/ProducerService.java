package com.stackroute.manualservice.listener;


import com.stackroute.manualservice.domain.QuestionDTO;
import com.stackroute.manualservice.domain.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    private KafkaTemplate<Object,Object> kafkaTemplate;

    @Autowired
    public ProducerService(KafkaTemplate<Object,Object> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTemplate(QuestionDTO questionDTO){

        kafkaTemplate.send("updated_query", questionDTO);
    }
}
