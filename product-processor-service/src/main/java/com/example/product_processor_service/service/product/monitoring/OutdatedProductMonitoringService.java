package com.example.product_processor_service.service.product.monitoring;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.product_processor_service.configuration.kafka.environment.KafkaEnvironment;
import com.example.product_processor_service.service.common.kafka.producer.KafkaProducerService;
import com.example.product_processor_service.service.product.ProductService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class OutdatedProductMonitoringService implements ProductMonitoringService {
    private final ProductService<UserProtoConfiguration.ProductMessage> productService;
    private final KafkaProducerService kafkaProducerService;
    private final KafkaEnvironment kafkaEnvironment;

    public OutdatedProductMonitoringService(ProductService<UserProtoConfiguration.ProductMessage> productService, KafkaProducerService kafkaProducerService, KafkaEnvironment kafkaEnvironment) {
        this.productService = productService;
        this.kafkaProducerService = kafkaProducerService;
        this.kafkaEnvironment = kafkaEnvironment;
    }
    @Override
    @Scheduled(initialDelay = 1000, fixedDelay = 60000)
    public void monitor() {
        System.out.println("outdated product monitoring");
        for(UserProtoConfiguration.ProductMessage p: productService.getAllExpired()) {
            System.out.println("got outdated products");
            kafkaProducerService.send(new ProducerRecord<>(kafkaEnvironment.getKAFKA_TOPIC_PRODUCT_UPDATE_NAME(), p.getUrl()));
        }
    }
}