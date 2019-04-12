package com.stackroute.graphqueryservice.config;

import com.stackroute.graphqueryservice.domain.QuestionDTO;
import com.stackroute.graphqueryservice.service.GraphQueryServiceImpl;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    private GraphQueryServiceImpl graphQueryService;

    @Autowired
    public KafkaConsumerConfig(GraphQueryServiceImpl graphQueryService) {
        this.graphQueryService = graphQueryService;
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


    @KafkaListener(id = "graphGroup", topics = "updated_query")
    public void listen(QuestionDTO questionDTO) {

        logger.info("Inside Kafka Consumer ******Received: " + questionDTO);
        System.out.println(questionDTO.getAnswer());
        System.out.println(questionDTO.getQuestion());
        System.out.println(questionDTO.getConcept());
        System.out.println(graphQueryService.getClass());
        graphQueryService.createNodesAndRelationships(questionDTO.getConcept(),questionDTO.getQuestion(),questionDTO.getAnswer());

        System.out.println("Relationship created");

    }

    @Bean
    public NewTopic topic() {

        return new NewTopic("update_query", 1, (short) 1);
    }



}
