package com.example.product_processor_service.service.account.product.grpc.client;

import com.example.grpc.user.AccountProductProtoServiceGrpc;
import org.springframework.stereotype.Service;

@Service
public class AccountProductGrpcClientServiceManager extends AccountProductGrpcClientService {
    public AccountProductGrpcClientServiceManager(AccountProductProtoServiceGrpc.AccountProductProtoServiceBlockingStub stub) {
        super(stub);
    }
}
