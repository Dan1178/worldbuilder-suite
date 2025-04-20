package com.worldbuilder.worldbuilder_suite.controller;

import com.worldbuilder.worldbuilder_suite.service.ImageGeneratorService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/image")
@CrossOrigin(origins = "http://localhost:5173")
public class ImageGeneratorController {
    private final ImageGeneratorService service;

    public ImageGeneratorController(ImageGeneratorService service) {
        this.service = service;
    }

    @PostMapping("/generate")
    public String generateImage(@RequestBody String description) {
        return service.generateImage(description);
    }
}