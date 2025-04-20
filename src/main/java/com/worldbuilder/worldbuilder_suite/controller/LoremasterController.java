package com.worldbuilder.worldbuilder_suite.controller;

import com.worldbuilder.worldbuilder_suite.service.LoremasterService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/loremaster")
@CrossOrigin(origins = "http://localhost:5173")
public class LoremasterController {
    private final LoremasterService service;

    public LoremasterController(LoremasterService service) {
        this.service = service;
    }

    @PostMapping("/answer")
    public String answerQuestion(@RequestBody String question) {
        return service.answerQuestion(question);
    }
}
