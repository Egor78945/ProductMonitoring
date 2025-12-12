package com.example.authentication_service.service.account;

import com.example.authentication_service.service.account.grpc.client.AccountGrpcClientService;
import org.springframework.stereotype.Service;

@Service
public class AccountProtoServiceManager extends AccountProtoService{
    public AccountProtoServiceManager(AccountGrpcClientService accountGrpcClientService) {
        super(accountGrpcClientService);
    }
}
