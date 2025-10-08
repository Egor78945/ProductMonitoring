package com.example.product_manager_service.service.kafka.template.router

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.io.Serializable
import kotlin.reflect.KClass

@Service
interface KafkaTemplateRouter {
    fun <T : Serializable> route(valueClass: KClass<T>): KafkaTemplate<String, T>
}