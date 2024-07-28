package com.data.test.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;


@Configuration
public class KafkaConfig {

    @Value("topic1")
    private String topicName;

    @Value("topic1")
    private String topicJsonName;

    @Bean
    public NewTopic Topic(){
        return TopicBuilder.name(topicName)
                .build();
    }

    @Bean
    public NewTopic JsonTopic(){
        return TopicBuilder.name(topicJsonName)
                .build();
    }
}
