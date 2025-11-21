package com.example.product_manager_service.service.product

import com.example.product_manager_service.configuration.kafka.environment.KafkaEnvironment
import com.example.product_manager_service.model.product.dto.ProductPublisherDto
import com.example.product_manager_service.service.kafka.producter.KafkaProducerService
import org.springframework.stereotype.Service

@Service
class AsyncKafkaProductPublisherRegistrationServiceManager(
    kafkaProducerService: KafkaProducerService,
    kafkaEnvironment: KafkaEnvironment
) : AsyncKafkaProductPublisherRegistrationService(kafkaProducerService, kafkaEnvironment) {
    override fun register(product: ProductPublisherDto) {
        when (product.productUri != null && product.publisherAccountUuid != null) {
            true ->
                super.register(product)

            false -> throw RuntimeException("product information is not initialized")
        }
    }
}