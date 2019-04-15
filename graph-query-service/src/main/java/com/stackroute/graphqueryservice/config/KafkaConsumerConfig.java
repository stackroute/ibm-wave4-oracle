package com.stackroute.graphqueryservice.config;

import com.stackroute.graphqueryservice.domain.QuestionDTO;
import com.stackroute.graphqueryservice.service.GraphQueryService;
import com.stackroute.graphqueryservice.service.GraphQueryServiceImpl;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.StringDeserializer;
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
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    private GraphQueryService graphQueryService;

    @Autowired
    public KafkaConsumerConfig(GraphQueryService graphQueryService) {
        this.graphQueryService = graphQueryService;
    }

     //Declaration


    //Declaration

    private final Logger logger = LoggerFactory.getLogger(KafkaConsumerConfig.class);

    // Consumer factory method

    @Bean
    public ConsumerFactory<String, String> consumerFactory () {
        Map<String, Object> config = new HashMap<>();

        config.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        config.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config);
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory () {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }


    @Bean
    public ConsumerFactory<String, QuestionDTO> userConsumerFactory () {
        Map<String, Object> config = new HashMap<>();

        config.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, "graphGroup");
        config.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(QuestionDTO.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, QuestionDTO> userKafkaListenerFactory () {
        ConcurrentKafkaListenerContainerFactory<String, QuestionDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userConsumerFactory());
        return factory;
    }


//    @KafkaListener(id = "graphGroup", topics = "updated_query")
//    public void listen(QuestionDTO questionDTO) {
//
//        logger.info("Inside Kafka Consumer ******Received: " + questionDTO);
//        System.out.println(questionDTO.getAnswer());
//        System.out.println(questionDTO.getQuestion());
//        System.out.println(questionDTO.getConcept());
//        System.out.println(graphQueryService.getClass());
//        graphQueryService.createNodesAndRelationships(questionDTO.getConcept(),questionDTO.getQuestion(),questionDTO.getAnswer());
//
//        System.out.println("Relationship created");
//
//    }
//
//    @Bean
//    public NewTopic topic() {
//
//        return new NewTopic("update_query", 1, (short) 1);
//    }



}
