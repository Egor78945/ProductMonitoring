package com.example.authentication_service.controller.authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {
    @GetMapping
    public String get() {
        System.out.println("CONTEEEEEEXT");
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        return "test";
    }
}
