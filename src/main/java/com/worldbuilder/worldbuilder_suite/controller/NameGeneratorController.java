package com.worldbuilder.worldbuilder_suite.controller;

import com.worldbuilder.worldbuilder_suite.service.NameGeneratorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/name")
public class NameGeneratorController {
    private final NameGeneratorService service;

    public NameGeneratorController(NameGeneratorService service) {
        this.service = service;
    }

    @PostMapping("/generate")
    public String generateNames(@RequestBody String description) {
        return service.generateNames(description);
    }
}