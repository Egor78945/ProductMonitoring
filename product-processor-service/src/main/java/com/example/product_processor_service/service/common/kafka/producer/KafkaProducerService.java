package com.example.product_processor_service.service.common.kafka.producer;

import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.Serializable;

public interface KafkaProducerService {
    void send(ProducerRecord<String, ? extends Serializable> producerRecord);
}
