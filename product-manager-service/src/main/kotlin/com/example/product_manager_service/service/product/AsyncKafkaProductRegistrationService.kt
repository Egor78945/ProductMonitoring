package com.example.product_manager_service.service.product

import com.example.product_manager_service.configuration.kafka.environment.KafkaEnvironment
import com.example.product_manager_service.service.kafka.producter.KafkaProducerService

abstract class AsyncKafkaProductRegistrationService<P>(
    protected val kafkaProducerService: KafkaProducerService,
    protected val kafkaEnvironment: KafkaEnvironment
) : AsyncProductRegistrationService<P>() {

}
