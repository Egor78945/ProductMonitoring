package com.example.product_processor_service.configuration.kafka.topic.initializer;

import com.example.product_processor_service.configuration.kafka.environment.KafkaEnvironment;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicInitializer {
    private final KafkaEnvironment kafkaEnvironment;

    public KafkaTopicInitializer(KafkaEnvironment kafkaEnvironment) {
        this.kafkaEnvironment = kafkaEnvironment;
    }

    @Bean
    public NewTopic updateProductTopicInitializer() {
        return TopicBuilder
                .name(kafkaEnvironment.getKAFKA_TOPIC_PRODUCT_UPDATE_NAME())
                .replicas(kafkaEnvironment.getKAFKA_REPLICATION_FACTOR())
                .partitions(kafkaEnvironment.getKAFKA_REPLICATION_FACTOR())
                .build();
    }

    @Bean
    public NewTopic deleteAccountProductTopicInitializer() {
        return TopicBuilder
                .name(kafkaEnvironment.getKAFKA_TOPIC_ACCOUNT_PRODUCT_DELETE_NAME())
                .replicas(kafkaEnvironment.getKAFKA_REPLICATION_FACTOR())
                .partitions(kafkaEnvironment.getKAFKA_REPLICATION_FACTOR())
                .build();
    }
}
