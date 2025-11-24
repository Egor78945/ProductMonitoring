package com.example.product_manager_service.service.product

import com.example.product_manager_service.configuration.kafka.environment.KafkaEnvironment
import com.example.product_manager_service.model.product.dto.ProductPublisherDto
import com.example.product_manager_service.service.kafka.producter.KafkaProducerService
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.security.core.context.SecurityContextHolder
import java.net.URI

abstract class AsyncKafkaProductPublisherRegistrationService(
    kafkaProducerService: KafkaProducerService,
    kafkaEnvironment: KafkaEnvironment
) :
    AsyncKafkaProductRegistrationService<ProductPublisherDto>(kafkaProducerService, kafkaEnvironment) {
    override fun register(product: ProductPublisherDto) {
        kafkaProducerService.send(
            ProducerRecord<String, ProductPublisherDto>(
                kafkaEnvironment.KAFKA_TOPIC_PRODUCT_SAVE_NAME,
                ProductPublisherDto(product.publisherAccountUuid, product.productUri)
            )
        )
    }
}
