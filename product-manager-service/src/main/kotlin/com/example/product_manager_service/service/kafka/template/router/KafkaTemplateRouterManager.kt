package com.example.product_manager_service.service.kafka.template.router

import com.example.product_manager_service.model.product.dto.ProductPublisherDto
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.io.Serializable
import kotlin.reflect.KClass

@Service
class KafkaTemplateRouterManager(productPublisherKafkaTemplate: KafkaTemplate<*, ProductPublisherDto>) :
    KafkaTemplateRouter {
    private val routerMap = mutableMapOf<KClass<*>, KafkaTemplate<*, *>>()

    init {
        routerMap[ProductPublisherDto::class] = productPublisherKafkaTemplate
    }

    override fun <T : Serializable> route(valueClass: KClass<T>): KafkaTemplate<String, T> =
        routerMap[valueClass] as? KafkaTemplate<String, T>
            ?: throw IllegalArgumentException("unknown kafka template for: $valueClass")
}