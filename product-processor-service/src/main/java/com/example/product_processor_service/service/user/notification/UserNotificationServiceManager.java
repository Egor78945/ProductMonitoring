package com.example.product_processor_service.service.user.notification;

import com.example.product_processor_service.configuration.kafka.environment.KafkaEnvironment;
import com.example.product_processor_service.model.mail.dto.MailMessage;
import com.example.product_processor_service.service.common.kafka.producer.KafkaProducerService;
import com.example.product_processor_service.service.common.notification.NotificationService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserNotificationServiceManager implements NotificationService<MailMessage> {
    private final KafkaProducerService kafkaProducerService;
    private final KafkaEnvironment kafkaEnvironment;

    protected UserNotificationServiceManager(KafkaProducerService kafkaProducerService, KafkaEnvironment kafkaEnvironment) {
        this.kafkaProducerService = kafkaProducerService;
        this.kafkaEnvironment = kafkaEnvironment;
    }

    @Override
    public void sendMessage(MailMessage message) {
        kafkaProducerService.send(new ProducerRecord<>(kafkaEnvironment.getKAFKA_TOPIC_USER_NOTIFICATION_NAME(), message));
    }

    @Override
    public void sendMessage(List<MailMessage> message) {
        for(MailMessage mailMessage : message){
            sendMessage(mailMessage);
        }
    }
}
