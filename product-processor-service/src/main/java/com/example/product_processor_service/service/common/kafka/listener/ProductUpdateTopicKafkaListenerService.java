package com.example.product_processor_service.service.common.kafka.listener;

import com.example.product_processor_service.service.product.processor.ProductProcessorService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProductUpdateTopicKafkaListenerService implements KafkaListenerService<String, String> {
    private final ProductProcessorService productProcessorService;

    public ProductUpdateTopicKafkaListenerService(ProductProcessorService productProcessorService) {
        this.productProcessorService = productProcessorService;
    }

    @Override
    @KafkaListener(topics = "${kafka.topic.product.update.name}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "stringListenerContainerFactory")
    public void listen(ConsumerRecord<String, String> listenableObject) {
        productProcessorService.update(listenableObject.value());
    }
}
