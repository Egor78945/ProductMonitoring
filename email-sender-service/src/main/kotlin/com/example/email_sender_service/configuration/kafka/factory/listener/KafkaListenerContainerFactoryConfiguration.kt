package com.example.email_sender_service.configuration.kafka.factory.listener

import com.example.email_sender_service.model.mail.dto.MailMessage
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer

@Configuration
class KafkaListenerContainerFactoryConfiguration {
    @Bean
    fun mailMessageListenerContainerFactory(consumerFactory: ConsumerFactory<String, MailMessage>): KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, MailMessage>> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, MailMessage>()
        factory.consumerFactory = consumerFactory

        return factory
    }
}