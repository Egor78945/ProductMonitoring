package com.example.product_manager_service.configuration.kafka.topic.initializer

import com.example.product_manager_service.configuration.kafka.environment.KafkaEnvironment
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

@Configuration
class KafkaTopicInitializer(private val kafkaEnvironment: KafkaEnvironment) {
    @Bean
    fun productSaveTopicInitializer(): NewTopic =
        TopicBuilder
            .name(kafkaEnvironment.KAFKA_TOPIC_PRODUCT_SAVE_NAME)
            .replicas(kafkaEnvironment.KAFKA_REPLICATION_FACTOR)
            .partitions(kafkaEnvironment.KAFKA_REPLICATION_FACTOR)
            .build()
}