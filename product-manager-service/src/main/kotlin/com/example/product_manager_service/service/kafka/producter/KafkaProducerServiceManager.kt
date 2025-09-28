package com.example.product_manager_service.service.kafka.producter

import com.example.product_manager_service.service.kafka.template.router.KafkaTemplateRouter
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.stereotype.Service
import java.io.Serializable
import kotlin.reflect.KClass

@Service
class KafkaProducerServiceManager(private val kafkaTemplateRouter: KafkaTemplateRouter) :
    KafkaProducerService {
    override fun send(producerRecord: ProducerRecord<String, out Serializable>) {
        kafkaTemplateRouter
            .route(producerRecord.value()::class as KClass<Serializable>)
            .send(producerRecord.topic(),producerRecord.value())
    }
}
