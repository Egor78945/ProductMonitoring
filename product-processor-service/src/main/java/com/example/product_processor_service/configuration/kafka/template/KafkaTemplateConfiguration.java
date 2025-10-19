package com.example.product_processor_service.configuration.kafka.template;

import com.example.product_processor_service.model.mail.dto.MailMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaTemplateConfiguration {
    @Bean
    public KafkaTemplate<String, String> stringKafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public KafkaTemplate<String, MailMessage> mailMessageTemplate(ProducerFactory<String, MailMessage> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
