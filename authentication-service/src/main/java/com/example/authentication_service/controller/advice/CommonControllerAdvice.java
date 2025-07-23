package com.example.authentication_service.controller.advice;

import com.example.authentication_service.controller.advice.handler.CommonControllerExceptionHandler;
import com.example.authentication_service.exception.message.ExceptionMessage;
import io.grpc.StatusRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(annotations = CommonControllerExceptionHandler.class)
public class CommonControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validationExceptionHandler(MethodArgumentNotValidException e) {
        var map = new HashMap<String, String>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            map.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StatusRuntimeException.class)
    public ResponseEntity<Map<String, Object>> statusRuntimeExceptionHandler(StatusRuntimeException e) {
        return new ResponseEntity<>(Map.of(ExceptionMessage.GRPC_ERROR.getMessage(), e.getMessage(), "stack trace", e.getStackTrace()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> runtimeExceptionHandler(RuntimeException e) {
        String message = e.getMessage() == null ? Arrays.toString(e.getStackTrace()) : e.getMessage();
        return new ResponseEntity<>(Map.of(ExceptionMessage.UNKNOWN.getMessage(), message, "stack trace", e.getStackTrace()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
