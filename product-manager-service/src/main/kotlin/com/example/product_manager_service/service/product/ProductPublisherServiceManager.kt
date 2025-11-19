package com.example.product_manager_service.service.product

import com.example.product_manager_service.configuration.kafka.environment.KafkaEnvironment
import com.example.product_manager_service.model.product.dto.ProductPublisherDto
import com.example.product_manager_service.service.kafka.producter.KafkaProducerService
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.stereotype.Service

@Service
class ProductPublisherServiceManager(
    private val kafkaProducerService: KafkaProducerService,
    private val kafkaEnvironment: KafkaEnvironment
) : ProductService<ProductPublisherDto> {
    override fun save(entity: ProductPublisherDto) {
        when (entity.productUri != null && entity.publisherAccountUuid != null) {
            true ->
                kafkaProducerService.send(
                    ProducerRecord<String, ProductPublisherDto>(
                        kafkaEnvironment.KAFKA_TOPIC_PRODUCT_SAVE_NAME,
                        entity
                    )
                )

            false -> throw RuntimeException("product information is not initialized")
        }
    }
}