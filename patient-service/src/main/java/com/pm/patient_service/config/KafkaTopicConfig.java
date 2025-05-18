package com.pm.patient_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic patientTopic() {
        return TopicBuilder.name("patient")
                .partitions(3)
                .replicas(1)
                .build();
    }

}
