package com.example.publisher.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    // Creează topic-ul automat la pornirea aplicației
    @Bean
    public NewTopic productsTopic() {
        return TopicBuilder.name("products-topic")
                .partitions(3)           // 3 partiții pentru paralelism
                .replicas(1)              // 1 replică (pentru dev)
                .compact()                 // Compaction policy
                .build();
    }

    @Bean
    public NewTopic notificationsTopic() {
        return TopicBuilder.name("notifications-topic")
                .partitions(2)
                .replicas(1)
                .build();
    }
}