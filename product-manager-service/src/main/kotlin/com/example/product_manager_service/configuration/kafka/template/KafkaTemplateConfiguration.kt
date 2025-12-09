package com.example.product_manager_service.configuration.kafka.template

import com.example.product_manager_service.model.product.dto.ProductPublisherDto
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class KafkaTemplateConfiguration() {
    @Bean
    fun productPublisherKafkaTemplate(producerFactory: ProducerFactory<String, ProductPublisherDto>): KafkaTemplate<String, ProductPublisherDto> =
        KafkaTemplate(producerFactory)
}