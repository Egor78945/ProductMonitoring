package com.example.product_processor_service.service.common.messaging;

import java.util.List;

public interface MessagingService<M> {
    void sendMessage(M message);
    void sendMessage(List<M> message);
}
