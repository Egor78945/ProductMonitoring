package com.example.product_manager_service.service.kafka.producter

import org.apache.kafka.clients.producer.ProducerRecord
import java.io.Serializable

interface KafkaProducerService {
    fun send(producerRecord: ProducerRecord<String, out Serializable>)
}