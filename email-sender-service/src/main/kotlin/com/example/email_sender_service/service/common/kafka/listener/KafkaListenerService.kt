package com.example.email_sender_service.service.common.kafka.listener

import org.apache.kafka.clients.consumer.ConsumerRecord
import java.io.Serializable

interface KafkaListenerService<K : Serializable, V : Serializable> {
    fun listen(consumerRecord: ConsumerRecord<K, V>)
}