package com.example.email_sender_service.model.mail.dto

class MailMessage {
    private lateinit var receiver: String
    private lateinit var messageText: String get set

    constructor(receiver: String, messageText: String) {
        this.receiver = receiver
        this.messageText = messageText
    }

    constructor() {

    }

    fun getReceiver() : String {
        return receiver
    }

    fun setReceiver(receiver: String) {
        this.receiver = receiver
    }

    fun getMessageText() : String {
        return messageText
    }

    fun setMessageText(messageText: String) {
        this.messageText = messageText
    }

}