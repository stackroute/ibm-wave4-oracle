package com.stackroute.manualservice.listener;


import com.stackroute.manualservice.domain.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    private KafkaTemplate<String,UserQuery> kafkaTemplate;

    @Autowired
    public ProducerService(KafkaTemplate<String,UserQuery> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTemplate(UserQuery query){

        kafkaTemplate.send("updated_query", query);
    }
}
