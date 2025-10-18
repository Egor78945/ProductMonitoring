package com.example.email_sender_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EmailSenderServiceApplication

fun main(args: Array<String>) {
    runApplication<EmailSenderServiceApplication>(*args)
}
