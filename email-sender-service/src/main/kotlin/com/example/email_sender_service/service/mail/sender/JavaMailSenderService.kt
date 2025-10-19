package com.example.email_sender_service.service.mail.sender

import com.example.email_sender_service.model.mail.dto.MailMessage
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender

abstract class JavaMailSenderService(val javaMailSender: JavaMailSender) : MailSenderService<MailMessage> {
    override fun send(message: MailMessage) {
        print("Sender: $javaMailSender")
        val smm = SimpleMailMessage()
        smm.setTo(message.getReceiver())
        smm.subject = "ProductMonitoringService"
        smm.text = message.getMessageText()
        javaMailSender.send(smm)
    }
}