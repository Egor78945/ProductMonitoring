package com.example.product_manager_service.service.kafka.producter

import com.example.product_manager_service.service.kafka.template.router.KafkaTemplateRouter
import org.apache.kafka.clients.producer.ProducerRecord
import java.io.Serializable
import kotlin.reflect.KClass

abstract class RoutedKafkaProducerService(protected val kafkaTemplateRouter: KafkaTemplateRouter) : KafkaProducerService {
    override fun send(producerRecord: ProducerRecord<String, out Serializable>) {
        kafkaTemplateRouter
            .route(producerRecord.value()::class as KClass<Serializable>)
            .send(producerRecord.topic(),producerRecord.value())
    }
}