package com.example.email_sender_service.service.common.kafka.listener

import com.example.email_sender_service.model.mail.dto.MailMessage
import com.example.email_sender_service.service.mail.sender.MailSenderService
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class MailMessageKafkaListenerServiceManager(private val mailSenderService: MailSenderService<MailMessage>) : KafkaListenerService<String, MailMessage> {
    @KafkaListener(
        topics = ["\${kafka.topic.user.notification.name}"],
        groupId = "\${spring.kafka.consumer.group-id}",
        containerFactory = "mailMessageListenerContainerFactory"
    )
    override fun listen(consumerRecord: ConsumerRecord<String, MailMessage>) {
        println("Consumer record: ${consumerRecord.value()}")
        mailSenderService.send(consumerRecord.value())
    }
}