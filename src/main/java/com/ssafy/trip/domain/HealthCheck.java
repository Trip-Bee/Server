package com.ssafy.trip.domain;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    @GetMapping("/api/healthcheck")
    public String healthCheck() {
        return "server is health";
    }
}
