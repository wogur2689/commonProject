package com.example.commonproject.main.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MainRestController {

    /**
     * ping
     */
    @GetMapping("/v1/api/ping")
    public String ping() {
        log.info("pong");
        return "pong";
    }
}
