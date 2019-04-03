package com.stackroute.manualservice.Config;

import com.stackroute.manualservice.domain.Query;
import com.stackroute.manualservice.service.ManualServiceImpl;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@Configuration
public class KafkaConsumerConfig {

    private ManualServiceImpl manualService;

    @Autowired
    public KafkaConsumerConfig(ManualServiceImpl manualService) {

        this.manualService = manualService;
    }

    //Declaration

    private final Logger logger = LoggerFactory.getLogger(KafkaConsumerConfig.class);

    // Consumer factory method

    @Bean
    public ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            ConsumerFactory<Object, Object> kafkaConsumerFactory,
            KafkaTemplate<Object, Object> template) {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, kafkaConsumerFactory);
        factory.setErrorHandler(new SeekToCurrentErrorHandler(
                new DeadLetterPublishingRecoverer(template), 3)); // dead-letter after 3 tries
        return factory;
    }

    @Bean
    public RecordMessageConverter converter() {

        return new StringJsonMessageConverter();
    }


    @KafkaListener(id = "queryGroup", topics = "new_query")
    public void listen(Query query) {

        logger.info("Received: " + query);

        manualService.saveQuestion(query);
//        if (query.getId().startsWith("fail")) {
//            throw new RuntimeException("failed");
//        } else {
//
//
//
//        }

    }


    @KafkaListener(id = "dltGroup", topics = "topic1.DLT")
    public void dltListen(String in) {

        logger.info("Received from DLT: " + in);
    }

    @Bean
    public NewTopic topic() {

        return new NewTopic("new_query", 1, (short) 1);
    }

    @Bean
    public NewTopic dlt() {

        return new NewTopic("topic1.DLT", 1, (short) 1);
    }
}
