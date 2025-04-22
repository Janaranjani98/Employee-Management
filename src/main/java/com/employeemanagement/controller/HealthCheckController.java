package com.employeemanagement.controller;

import com.employeemanagement.entity.HealthCheckResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/healthcheck/up")
public class HealthCheckController {

    @Value("${appId}")
    private String appId;

    @Value("${version}")
    private String version;

    @GetMapping
    public HealthCheckResponse getHealth(){
        HealthCheckResponse response = new HealthCheckResponse(appId,version,"UP");
        return response;
    }
}
