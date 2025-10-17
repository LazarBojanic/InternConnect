package com.internconnect.internconnectbackendspring.service;

import com.internconnect.internconnectbackendspring.repository.MainRepository;
import org.springframework.stereotype.Service;

@Service
public class MainService {
    private final MainRepository mainRepository;
    public MainService(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }
    public String sayHello() {
        return "Hello World!";
    }
}
