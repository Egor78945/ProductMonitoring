package com.example.product_processor_service.service.account.product;

import com.example.product_processor_service.service.account.product.grpc.client.AccountProductGrpcClientService;
import org.springframework.stereotype.Service;

@Service
public class AccountProductProtoServiceManager extends AccountProductProtoService{
    public AccountProductProtoServiceManager(AccountProductGrpcClientService accountProductGrpcClientService) {
        super(accountProductGrpcClientService);
    }
}
