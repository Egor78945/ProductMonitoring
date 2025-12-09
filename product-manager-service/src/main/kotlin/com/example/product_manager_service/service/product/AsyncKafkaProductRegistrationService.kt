package com.example.product_manager_service.service.product

import com.example.product_manager_service.configuration.kafka.environment.KafkaEnvironment
import com.example.product_manager_service.model.product.dto.ProductPublisherDto
import com.example.product_manager_service.service.kafka.producter.KafkaProducerService
import org.apache.kafka.clients.producer.ProducerRecord
import java.io.Serializable

abstract class AsyncKafkaProductRegistrationService(
    protected val kafkaProducerService: KafkaProducerService,
    protected val kafkaEnvironment: KafkaEnvironment
) : AsyncProductRegistrationService<ProductPublisherDto>() {
    override fun register(subject: ProductPublisherDto) {
        kafkaProducerService.send(ProducerRecord<String, Serializable>(kafkaEnvironment.KAFKA_TOPIC_PRODUCT_SAVE_NAME, subject))
    }

    override fun delete(subject: ProductPublisherDto) {
        kafkaProducerService.send(ProducerRecord<String, Serializable>(kafkaEnvironment.KAFKA_TOPIC_ACCOUNT_PRODUCT_DELETE_NAME, subject))
    }
}
