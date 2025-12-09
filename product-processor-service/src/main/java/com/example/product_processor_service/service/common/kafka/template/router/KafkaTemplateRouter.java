package com.example.product_processor_service.service.common.kafka.template.router;

import org.springframework.kafka.core.KafkaTemplate;

import java.io.Serializable;

public interface KafkaTemplateRouter {
    <V extends Serializable> KafkaTemplate<String, V> route(Class<V> clazz);
}
