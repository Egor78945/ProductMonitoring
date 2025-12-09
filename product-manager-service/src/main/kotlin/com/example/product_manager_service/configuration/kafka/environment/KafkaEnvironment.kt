package com.example.product_manager_service.configuration.kafka.environment

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
data class KafkaEnvironment(
    @Value("\${kafka.topic.product.save.name}") val KAFKA_TOPIC_PRODUCT_SAVE_NAME: String,
    @Value("\${kafka.topic.account.product.delete}") val KAFKA_TOPIC_ACCOUNT_PRODUCT_DELETE_NAME: String,
    @Value("\${kafka.replicas}") val KAFKA_REPLICATION_FACTOR: Int,
    @Value("\${spring.kafka.bootstrap-servers}") val KAFKA_BOOTSTRAP_SERVERS: String,
    @Value("\${spring.kafka.producer.retries}") val KAFKA_PRODUCER_RETRIES: Int
) {
}