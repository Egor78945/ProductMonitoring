package com.example.product_manager_service.service.product

import com.example.product_manager_service.configuration.kafka.environment.KafkaEnvironment
import com.example.product_manager_service.model.product.dto.ProductPublisherDto
import com.example.product_manager_service.service.kafka.producter.KafkaProducerService
import com.example.product_manager_service.service.marketplace.validation.MarketplaceValidationService
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.stereotype.Service
import java.net.URI

@Service
class ProductUriServiceManager(
    private val marketplaceValidationService: MarketplaceValidationService<URI>,
    private val kafkaProducerService: KafkaProducerService,
    private val kafkaEnvironment: KafkaEnvironment
) : ProductService<URI> {
    override fun save(publisherUsername: String, product: URI) {
        if (marketplaceValidationService.isSupported(product))
            kafkaProducerService.send(
                ProducerRecord<String, ProductPublisherDto>(
                    kafkaEnvironment.KAFKA_TOPIC_PRODUCT_SAVE_NAME,
                    ProductPublisherDto(publisherUsername, product.toString())
                )
            )
        else
            throw RuntimeException("unsupported marketplace ${product.host}")
    }
}