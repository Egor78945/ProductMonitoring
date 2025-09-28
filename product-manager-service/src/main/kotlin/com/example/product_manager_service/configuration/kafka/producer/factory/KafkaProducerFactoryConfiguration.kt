package com.example.product_manager_service.configuration.kafka.producer.factory

import com.example.product_manager_service.configuration.kafka.environment.KafkaEnvironment
import com.example.product_manager_service.model.product.dto.ProductPublisherDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer


@Configuration
class KafkaProducerFactoryConfiguration(private val kafkaEnvironment: KafkaEnvironment) {
    @Bean
    fun productPublisherProducerFactory(objectMapper: ObjectMapper): ProducerFactory<String, ProductPublisherDto> {
        val cfg = DefaultKafkaProducerFactory<String, ProductPublisherDto>(buildAtLeastOnceProducerProperties())
        cfg.valueSerializer = JsonSerializer(objectMapper)
        return cfg
    }

    @Bean
    fun objectMapper(): ObjectMapper = ObjectMapper()

    private fun buildAtLeastOnceProducerProperties(): Map<String, *> {
        val producerProperties: MutableMap<String, Any> = HashMap()
        producerProperties.put(
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
            StringSerializer().javaClass.name
        )
        producerProperties.put(
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
            JsonSerializer::class
        )
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaEnvironment.KAFKA_BOOTSTRAP_SERVERS)
        producerProperties.put(ProducerConfig.ACKS_CONFIG, "1")
        producerProperties.put(ProducerConfig.RETRIES_CONFIG, kafkaEnvironment.KAFKA_PRODUCER_RETRIES)

        return producerProperties
    }
}