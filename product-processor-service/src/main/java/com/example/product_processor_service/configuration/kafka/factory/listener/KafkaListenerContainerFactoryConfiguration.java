package com.example.product_processor_service.configuration.kafka.factory.listener;

import com.example.product_processor_service.model.product.ProductPublisherDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

@Configuration
public class KafkaListenerContainerFactoryConfiguration {
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, ProductPublisherDTO>> productPublisherListenerContainerFactory(ConsumerFactory<String, ProductPublisherDTO> consumerFactory) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, ProductPublisherDTO>();
        factory.setConsumerFactory(consumerFactory);

        return factory;
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> stringListenerContainerFactory(ConsumerFactory<String, String> consumerFactory) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        factory.setConsumerFactory(consumerFactory);

        return factory;
    }
}
