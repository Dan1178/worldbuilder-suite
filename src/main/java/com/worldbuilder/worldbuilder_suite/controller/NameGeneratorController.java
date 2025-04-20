package com.worldbuilder.worldbuilder_suite.controller;

import com.worldbuilder.worldbuilder_suite.service.NameGeneratorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/name")
@CrossOrigin(origins = "http://localhost:5173")
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