package com.example.product_processor_service.service.common.kafka.producer;

import com.example.product_processor_service.service.common.kafka.template.router.KafkaTemplateRouter;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class KafkaProducerServiceManager implements KafkaProducerService {
    private final KafkaTemplateRouter kafkaTemplateRouter;

    public KafkaProducerServiceManager(KafkaTemplateRouter kafkaTemplateRouter) {
        this.kafkaTemplateRouter = kafkaTemplateRouter;
    }

    @Override
    public void send(ProducerRecord<String, ? extends Serializable> producerRecord) {
        sendWithTemplate(kafkaTemplateRouter.route(producerRecord.value().getClass()), producerRecord);
    }

    private <V extends Serializable> void sendWithTemplate(
            KafkaTemplate<String, V> template,
            ProducerRecord<String, ? extends Serializable> record) {
        template.send(record.topic(), (V) record.value());
    }
}
