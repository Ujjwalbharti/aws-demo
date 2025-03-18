package com.example.awsdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/sqs")
public class HealthCheck {

    @GetMapping("health")
    public String health() {
        return "Aws demo Service is up and running";
    }
}
