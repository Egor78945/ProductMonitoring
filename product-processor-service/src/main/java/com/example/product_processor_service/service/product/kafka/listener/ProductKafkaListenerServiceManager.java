package com.example.product_processor_service.service.product.kafka.listener;

import com.example.product_processor_service.model.product.ProductPublisherDTO;
import com.example.product_processor_service.service.common.kafka.listener.KafkaListenerService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProductKafkaListenerServiceManager implements KafkaListenerService<String, ProductPublisherDTO> {
    @Override
    @KafkaListener(topics = "${kafka.topic.product.save.name}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "productPublisherListenerContainerFactory")
    public void listen(ConsumerRecord<String, ProductPublisherDTO> listenableObject) {
        System.out.println(listenableObject.value());
    }
}
