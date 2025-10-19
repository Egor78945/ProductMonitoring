package com.example.product_processor_service.model.mail.dto;

import java.io.Serializable;

public class MailMessage implements Serializable {
    private String receiver;
    private String messageText;

    public MailMessage(String receiver, String messageText) {
        this.receiver = receiver;
        this.messageText = messageText;
    }

    public MailMessage() {
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
