package com.example.email_sender_service.service.mail.sender

interface MailSenderService<M> {
    fun send(message: M)
}
