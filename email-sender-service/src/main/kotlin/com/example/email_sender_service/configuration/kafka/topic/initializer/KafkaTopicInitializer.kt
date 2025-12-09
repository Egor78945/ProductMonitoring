package com.example.email_sender_service.configuration.kafka.topic.initializer

import com.example.email_sender_service.configuration.kafka.environment.KafkaEnvironment
import org.springframework.context.annotation.Bean
import org.springframework.kafka.config.TopicBuilder
import org.springframework.stereotype.Component

@Component
class KafkaTopicInitializer(private val kafkaEnvironment: KafkaEnvironment) {
    @Bean
    fun userNotificationTopic() = TopicBuilder
        .name(kafkaEnvironment.KAFKA_TOPIC_USER_NOTIFCATION_NAME)
        .partitions(kafkaEnvironment.KAFKA_REPLICATION_FACTOR)
        .replicas(kafkaEnvironment.KAFKA_REPLICATION_FACTOR)
        .build()
}