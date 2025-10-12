package com.example.product_processor_service.configuration.kafka.factory.consumer;

import com.example.product_processor_service.configuration.kafka.environment.KafkaEnvironment;
import com.example.product_processor_service.model.product.ProductPublisherDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerFactoryConfiguration {
    private final KafkaEnvironment kafkaEnvironment;

    public KafkaConsumerFactoryConfiguration(KafkaEnvironment kafkaEnvironment) {
        this.kafkaEnvironment = kafkaEnvironment;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public ConsumerFactory<String, ProductPublisherDTO> transactionConsumerFactory(ObjectMapper objectMapper) {
        var kafkaConsumerFactory = new DefaultKafkaConsumerFactory<String, ProductPublisherDTO>(buildKafkaConsumerProperties("com.example.product_manager_service.model.product.dto.ProductPublisherDto", "com.example.product_processor_service.model.product.ProductPublisherDTO", kafkaEnvironment.getKAFKA_GROUP_ID()));
        kafkaConsumerFactory.setKeyDeserializer(new StringDeserializer());
        kafkaConsumerFactory.setValueDeserializer(new JsonDeserializer<>(objectMapper));

        return kafkaConsumerFactory;
    }

    @Bean
    public ConsumerFactory<String, String> stringConsumerFactory(ObjectMapper objectMapper) {
        var kafkaConsumerFactory = new DefaultKafkaConsumerFactory<String, String>(buildKafkaConsumerProperties("com.example.product_manager_service.model.product.dto.ProductPublisherDto", "com.example.product_processor_service.model.product.ProductPublisherDTO", kafkaEnvironment.getKAFKA_GROUP_ID()));
        kafkaConsumerFactory.setKeyDeserializer(new StringDeserializer());
        kafkaConsumerFactory.setValueDeserializer(new StringDeserializer());

        return kafkaConsumerFactory;
    }

    private Map<String, Object> buildKafkaConsumerProperties(String classPathFrom, String classPathTo, String groupId){
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.put(JsonDeserializer.TYPE_MAPPINGS, String.format("%s:%s", classPathFrom, classPathTo));
        properties.put(JsonDeserializer.TRUSTED_PACKAGES, "com.example.transaction_manager_service.*");
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaEnvironment.getKAFKA_BOOTSTRAP_SERVERS());
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return properties;
    }
}
