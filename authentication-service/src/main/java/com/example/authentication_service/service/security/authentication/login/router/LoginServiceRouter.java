package com.example.authentication_service.service.security.authentication.login.router;

import com.example.authentication_service.model.security.UserAuthenticationModel;
import com.example.authentication_service.service.security.authentication.login.LoginService;

public interface LoginServiceRouter<T extends UserAuthenticationModel> {
    LoginService<T,String> onUsername(String username);
}
