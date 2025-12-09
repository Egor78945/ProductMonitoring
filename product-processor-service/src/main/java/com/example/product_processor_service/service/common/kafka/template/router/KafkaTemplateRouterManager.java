package com.example.product_processor_service.service.common.kafka.template.router;

import com.example.product_processor_service.exception.NotFoundException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class KafkaTemplateRouterManager implements KafkaTemplateRouter {
    private final Map<Class<?>, KafkaTemplate<String, ?>> kafkaTemplateMap;

    public KafkaTemplateRouterManager(KafkaTemplate<String, String> stringKafkaTemplate) {
        kafkaTemplateMap = new HashMap<>();
        kafkaTemplateMap.put(String.class, stringKafkaTemplate);
    }

    @Override
    public <V extends Serializable> KafkaTemplate<String, V> route(Class<V> clazz) {
        return (KafkaTemplate<String, V>) Optional.ofNullable(kafkaTemplateMap.get(clazz))
                .orElseThrow(() -> new NotFoundException("unknown kafka template"));
    }
}
