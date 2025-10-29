package com.example.authentication_service.service.account.grpc.client;

import com.example.grpc.user.AccountProtoServiceGrpc;
import com.example.grpc.user.UserProtoConfiguration;
import org.springframework.stereotype.Service;

@Service
public class AccountGrpcClientServiceManager extends AccountGrpcClientService {
    public AccountGrpcClientServiceManager(AccountProtoServiceGrpc.AccountProtoServiceBlockingStub stub) {
        super(stub);
    }
}
