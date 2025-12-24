package com.example.product_processor_service.service.product;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.product_processor_service.model.product.ProductRegistrationModel;
import com.example.product_processor_service.service.RegistrationService;

public interface ProductRegistrationService extends RegistrationService<UserProtoConfiguration.ProductRegistrationMessage> {
}
