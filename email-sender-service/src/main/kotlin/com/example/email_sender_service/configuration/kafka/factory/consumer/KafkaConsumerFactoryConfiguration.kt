package com.example.email_sender_service.configuration.kafka.factory.consumer

import com.example.email_sender_service.configuration.kafka.environment.KafkaEnvironment
import com.example.email_sender_service.model.mail.dto.MailMessage
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer

@Configuration
class KafkaConsumerFactoryConfiguration(private val kafkaEnvironment: KafkaEnvironment) {
    @Bean
    fun objectMapper() = ObjectMapper()

    @Bean
    fun transactionConsumerFactory(objectMapper: ObjectMapper): ConsumerFactory<String, MailMessage> {
        val kafkaConsumerFactory: DefaultKafkaConsumerFactory<String, MailMessage> =
            DefaultKafkaConsumerFactory<String, MailMessage>(
                buildKafkaConsumerProperties(
                    "com.example.product_processor_service.model.mail.dto.MailMessage",
                    "com.example.email_sender_service.model.mail.dto.MailMessage",
                    kafkaEnvironment.KAFKA_TOPIC_USER_GROUP_ID
                )
            )
        kafkaConsumerFactory.setKeyDeserializer(StringDeserializer())
        kafkaConsumerFactory.setValueDeserializer(JsonDeserializer<MailMessage>(objectMapper))

        return kafkaConsumerFactory
    }

    private fun buildKafkaConsumerProperties(
        classPathFrom: String,
        classPathTo: String,
        groupId: String
    ): MutableMap<String, Any> {
        val properties: MutableMap<String, Any> = HashMap()
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId)
        properties.put(JsonDeserializer.TYPE_MAPPINGS, String.format("%s:%s", classPathFrom, classPathTo))
        properties.put(JsonDeserializer.TRUSTED_PACKAGES, "*")
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaEnvironment.KAFKA_BOOTSTRAP_SERVERS)
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")

        return properties
    }
}