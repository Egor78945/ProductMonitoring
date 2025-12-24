package com.example.product_processor_service.service.common.kafka.listener;

import com.example.async_spring_boot_starter.service.concurrency.AsyncStarterAsyncTaskExecutorService;
import com.example.product_processor_service.model.product.ProductPublisherDTO;
import com.example.product_processor_service.service.product.processor.ProductProcessorService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProductSaveTopicKafkaListenerServiceManager implements KafkaListenerService<String, ProductPublisherDTO> {
    private final ProductProcessorService productProcessorService;
    private final AsyncStarterAsyncTaskExecutorService taskExecutorService;

    public ProductSaveTopicKafkaListenerServiceManager(ProductProcessorService productProcessorService, AsyncStarterAsyncTaskExecutorService taskExecutorService) {
        this.productProcessorService = productProcessorService;
        this.taskExecutorService = taskExecutorService;
    }

    @Override
    @KafkaListener(topics = "${kafka.topic.product.save.name}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "productPublisherListenerContainerFactory")
    public void listen(ConsumerRecord<String, ProductPublisherDTO> listenableObject) {
        System.out.println("got new product = " + listenableObject.value());
        taskExecutorService.run(() -> productProcessorService.register(listenableObject.value()));
    }
}
