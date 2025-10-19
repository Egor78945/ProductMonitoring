package com.example.email_sender_service.service.mail.sender

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class JavaMailSenderServiceManager(javaMailSender: JavaMailSender): JavaMailSenderService(javaMailSender) {
}