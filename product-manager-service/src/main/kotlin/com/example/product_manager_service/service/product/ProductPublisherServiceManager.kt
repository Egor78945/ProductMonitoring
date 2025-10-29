package com.example.product_manager_service.service.product

import com.example.product_manager_service.configuration.kafka.environment.KafkaEnvironment
import com.example.product_manager_service.model.product.dto.ProductPublisherDto
import com.example.product_manager_service.service.kafka.producter.KafkaProducerService
import com.example.product_manager_service.service.marketplace.validation.MarketplaceValidationService
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.stereotype.Service
import java.net.URI

@Service
class ProductPublisherServiceManager(
    private val marketplaceValidationService: MarketplaceValidationService<URI>,
    private val kafkaProducerService: KafkaProducerService,
    private val kafkaEnvironment: KafkaEnvironment
) : ProductService<ProductPublisherDto> {
    override fun save(entity: ProductPublisherDto) {
        when (entity.isInitialized()) {
            true -> if (marketplaceValidationService.isSupported(
                    entity.productUri!!
                )
            ) {
                kafkaProducerService.send(
                    ProducerRecord<String, ProductPublisherDto>(
                        kafkaEnvironment.KAFKA_TOPIC_PRODUCT_SAVE_NAME,
                        entity
                    )
                )
            } else throw RuntimeException("unsupported marketplace ${entity.productUri.host}")

            false -> throw RuntimeException("product information is not initialized")
        }
    }
}