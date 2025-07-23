package com.example.authentication_service.controller.authentication.advice;

import com.example.authentication_service.controller.authentication.advice.handler.AuthenticationControllerExceptionHandler;
import com.example.authentication_service.exception.AuthenticationException;
import com.example.authentication_service.exception.message.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice(annotations = AuthenticationControllerExceptionHandler.class)
public class AuthenticationControllerAdvice {
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, String>> authenticationExceptionHandler(AuthenticationException e) {
        return new ResponseEntity<>(Map.of(ExceptionMessage.AUTHENTICATION_ERROR.getMessage(), e.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}
