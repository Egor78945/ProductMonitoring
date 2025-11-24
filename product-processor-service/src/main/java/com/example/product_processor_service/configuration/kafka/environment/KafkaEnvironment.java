package com.example.product_processor_service.configuration.kafka.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaEnvironment {
    private final String KAFKA_BOOTSTRAP_SERVERS;
    private final String KAFKA_TOPIC_PRODUCT_SAVE_NAME;
    private final String KAFKA_TOPIC_PRODUCT_UPDATE_NAME;
    private final String KAFKA_GROUP_ID;
    private final int KAFKA_REPLICATION_FACTOR;
    private final int KAFKA_PRODUCER_RETRY;

    public KafkaEnvironment(@Value("${kafka.topic.product.save.name}")
                            String KAFKA_TOPIC_PRODUCT_SAVE_NAME,
                            @Value("${kafka.topic.product.update.name}")
                            String kafkaTopicProductUpdateName,
                            @Value("${kafka.replicas}")
                            int KAFKA_REPLICATION_FACTOR,
                            @Value("${spring.kafka.bootstrap-servers}")
                            String KAFKA_BOOTSTRAP_SERVERS,
                            @Value("${spring.kafka.consumer.group-id}")
                            String KAFKA_GROUP_ID,
                            @Value("${spring.kafka.producer.retries}") int kafkaProducerRetry) {
        this.KAFKA_BOOTSTRAP_SERVERS = KAFKA_BOOTSTRAP_SERVERS;
        this.KAFKA_TOPIC_PRODUCT_SAVE_NAME = KAFKA_TOPIC_PRODUCT_SAVE_NAME;
        this.KAFKA_TOPIC_PRODUCT_UPDATE_NAME = kafkaTopicProductUpdateName;
        this.KAFKA_REPLICATION_FACTOR = KAFKA_REPLICATION_FACTOR;
        this.KAFKA_GROUP_ID = KAFKA_GROUP_ID;
        KAFKA_PRODUCER_RETRY = kafkaProducerRetry;
    }

    public int getKAFKA_PRODUCER_RETRY() {
        return KAFKA_PRODUCER_RETRY;
    }

    public String getKAFKA_TOPIC_PRODUCT_UPDATE_NAME() {
        return KAFKA_TOPIC_PRODUCT_UPDATE_NAME;
    }


    public String getKAFKA_BOOTSTRAP_SERVERS() {
        return KAFKA_BOOTSTRAP_SERVERS;
    }

    public String getKAFKA_TOPIC_PRODUCT_SAVE_NAME() {
        return KAFKA_TOPIC_PRODUCT_SAVE_NAME;
    }

    public int getKAFKA_REPLICATION_FACTOR() {
        return KAFKA_REPLICATION_FACTOR;
    }

    public String getKAFKA_GROUP_ID() {
        return KAFKA_GROUP_ID;
    }
}
