package com.example.product_processor_service.service.common.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.io.Serializable;

public interface KafkaListenerService <K extends Serializable, V extends Serializable> {
    void listen(ConsumerRecord<K, V> listenableObject);
}
