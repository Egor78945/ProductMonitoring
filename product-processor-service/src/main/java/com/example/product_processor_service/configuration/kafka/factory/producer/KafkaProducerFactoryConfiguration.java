package com.example.product_processor_service.configuration.kafka.factory.producer;

import com.example.product_processor_service.configuration.kafka.environment.KafkaEnvironment;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerFactoryConfiguration {
    private final KafkaEnvironment kafkaEnvironment;

    public KafkaProducerFactoryConfiguration(KafkaEnvironment kafkaEnvironment) {
        this.kafkaEnvironment = kafkaEnvironment;
    }

    @Bean
    public ProducerFactory<String, String> productUrlProducerFactory() {
        DefaultKafkaProducerFactory<String, String> cfg = new DefaultKafkaProducerFactory<>(buildAtLeastOnceProducerProperties());
        cfg.setKeySerializer(new StringSerializer());
        cfg.setValueSerializer(new StringSerializer());
        return cfg;
    }

    private Map<String, Object> buildAtLeastOnceProducerProperties() {
        Map<String, Object> producerProperties = new HashMap<>();
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaEnvironment.getKAFKA_BOOTSTRAP_SERVERS());
        producerProperties.put(ProducerConfig.ACKS_CONFIG, "1");
        producerProperties.put(ProducerConfig.RETRIES_CONFIG, kafkaEnvironment.getKAFKA_PRODUCER_RETRY());

        return producerProperties;
    }
}
