package com.example.product_processor_service.service.common.kafka.listener;

import com.example.async_spring_boot_starter.service.concurrency.AsyncStarterAsyncTaskExecutorService;
import com.example.product_processor_service.service.product.processor.ProductProcessorService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProductUpdateTopicKafkaListenerService implements KafkaListenerService<String, String> {
    private final ProductProcessorService productProcessorService;
    private final AsyncStarterAsyncTaskExecutorService asyncStarterAsyncTaskExecutorService;

    public ProductUpdateTopicKafkaListenerService(ProductProcessorService productProcessorService, AsyncStarterAsyncTaskExecutorService asyncStarterAsyncTaskExecutorService) {
        this.productProcessorService = productProcessorService;
        this.asyncStarterAsyncTaskExecutorService = asyncStarterAsyncTaskExecutorService;
    }

    @Override
    @KafkaListener(topics = "${kafka.topic.product.update.name}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "stringListenerContainerFactory")
    public void listen(ConsumerRecord<String, String> listenableObject) {
        System.out.println("kafka update listener");
        asyncStarterAsyncTaskExecutorService.run(() -> productProcessorService.update(listenableObject.value()));
    }
}
