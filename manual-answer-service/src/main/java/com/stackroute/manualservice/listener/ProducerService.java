package com.stackroute.manualservice.listener;


import com.stackroute.manualservice.domain.QuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    private KafkaTemplate<String,QuestionDTO> kafkaTemplate;

    @Autowired
    public ProducerService(KafkaTemplate<String,QuestionDTO> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTemplate(QuestionDTO questionDTO){

        kafkaTemplate.send("updated_query", questionDTO);
    }
}
