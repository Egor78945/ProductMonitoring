package com.example.authentication_service.service.security.authentication.login.router;

import com.example.authentication_service.model.security.UserAuthenticationModel;
import com.example.authentication_service.service.security.authentication.login.LoginService;
import com.example.authentication_service.service.security.authentication.login.UserProtoOnAccountNameLoginService;
import com.example.authentication_service.service.security.authentication.login.UserProtoOnEmailLoginService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceRouterManager implements LoginServiceRouter<UserAuthenticationModel>{
    private Map<String, LoginService<UserAuthenticationModel,String>> router;

    public LoginServiceRouterManager(UserProtoOnAccountNameLoginService<UserAuthenticationModel> onAccountNameLoginService,
                                     UserProtoOnEmailLoginService<UserAuthenticationModel> onEmailLoginService) {
        router = new HashMap<>();
        router.put("email", onEmailLoginService);
        router.put("name", onAccountNameLoginService);
    }
    @Override
    public LoginService<UserAuthenticationModel, String> onUsername(String username) {
        return router.get(username);
    }
}
