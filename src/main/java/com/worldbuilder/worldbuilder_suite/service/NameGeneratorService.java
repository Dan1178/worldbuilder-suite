package com.worldbuilder.worldbuilder_suite.service;

import dev.langchain4j.model.huggingface.HuggingFaceChatModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class NameGeneratorService {

    @Value("${langchain4j.hugging-face.api-key}")
    private String huggingFaceApiKey;

    private HuggingFaceChatModel model;

    public String generateNames(String description) {
        this.model = HuggingFaceChatModel.builder()
                .accessToken(huggingFaceApiKey)
                .modelId("mistralai/Mixtral-8x7B-Instruct-v0.1")
                .build();
        try {
            String template = "Generate 25 unique names for a character described as: {{description}}. " +
                    "Each name must match the characteristics (e.g., race, nationality, vibe). " +
                    "Return ONLY the names as a comma-separated list, with no additional text, prefixes, or formatting.";
            PromptTemplate promptTemplate = PromptTemplate.from(template);
            Map<String, Object> variables = new HashMap<>();
            variables.put("description", description);
            Prompt prompt = promptTemplate.apply(variables);
            String rawResponse = model.generate(prompt.text());
            String[] responseLines = rawResponse.split("\n");
            return responseLines[responseLines.length - 1].trim();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate names: " + e.getMessage(), e);
        }
    }
}