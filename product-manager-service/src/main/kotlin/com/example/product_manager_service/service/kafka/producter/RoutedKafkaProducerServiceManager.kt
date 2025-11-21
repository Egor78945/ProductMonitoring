package com.example.product_manager_service.service.kafka.producter

import com.example.product_manager_service.service.kafka.template.router.KafkaTemplateRouter
import org.springframework.stereotype.Service

@Service
class RoutedKafkaProducerServiceManager(kafkaTemplateRouter: KafkaTemplateRouter) :
    RoutedKafkaProducerService(kafkaTemplateRouter) {
}
