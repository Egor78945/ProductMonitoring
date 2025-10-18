package com.example.email_sender_service.configuration.kafka.environment

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class KafkaEnvironment(
    @Value("\${kafka.topic.user.notification.name}") val KAFKA_TOPIC_USER_NOTIFCATION_NAME: String,
    @Value("\${spring.kafka.consumer.group-id}") val KAFKA_TOPIC_USER_GROUP_ID: String,
    @Value("\${kafka.replicas}") val KAFKA_REPLICATION_FACTOR: Int,
    @Value("\${spring.kafka.bootstrap-servers}") val KAFKA_BOOTSTRAP_SERVERS: String,
) {
}