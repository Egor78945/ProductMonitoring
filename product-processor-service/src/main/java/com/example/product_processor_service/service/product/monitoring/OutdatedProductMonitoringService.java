package com.example.product_processor_service.service.product.monitoring;

import com.example.product_processor_service.configuration.kafka.environment.KafkaEnvironment;
import com.example.product_processor_service.model.product.entity.Product;
import com.example.product_processor_service.repository.product.EntityProductRepository;
import com.example.product_processor_service.service.common.kafka.producer.KafkaProducerService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.jooq.DatePart;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class OutdatedProductMonitoringService implements ProductMonitoringService {
    private final EntityProductRepository entityProductRepository;
    private final KafkaProducerService kafkaProducerService;
    private final KafkaEnvironment kafkaEnvironment;

    public OutdatedProductMonitoringService(EntityProductRepository entityProductRepository, KafkaProducerService kafkaProducerService, KafkaEnvironment kafkaEnvironment) {
        this.entityProductRepository = entityProductRepository;
        this.kafkaProducerService = kafkaProducerService;
        this.kafkaEnvironment = kafkaEnvironment;
    }
    @Override
    @Scheduled(initialDelay = 1000, fixedDelay = 60000)
    public void monitor() {
        for(Product p: entityProductRepository.getAllExpired(1, DatePart.MINUTE, 10)) {
            System.out.println("got outdated products");
            kafkaProducerService.send(new ProducerRecord<>(kafkaEnvironment.getKAFKA_TOPIC_PRODUCT_UPDATE_NAME(), p.getUrl()));
        }
    }
}
