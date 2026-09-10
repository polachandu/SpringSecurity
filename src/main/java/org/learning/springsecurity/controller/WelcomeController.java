package org.learning.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/welcomeWithoutSecurity")
    public String sayWelcomeWithoutSecurity(){
        return "Welcome to Spring Application without security";
    }

    @GetMapping("/welcomeWithSecurity")
    public String sayWelcomeWithSecurity(){
        return "Welcome to Spring Application with security";
    }
}
