package com.example.product_processor_service.service.common.notification;

import java.util.List;

public interface NotificationService<M> {
    void sendMessage(M message);
    void sendMessage(List<M> message);
}
