package com.internconnect.internconnectbackendspring.controller;

import com.internconnect.internconnectbackendspring.service.MainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class MainController {
    private static Logger logger = LoggerFactory.getLogger(MainController.class);
    private final MainService mainService;
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }
    @GetMapping("/say-hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok(mainService.sayHello());
    }
}
