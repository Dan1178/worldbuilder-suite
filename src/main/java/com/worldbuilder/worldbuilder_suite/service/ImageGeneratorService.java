package com.worldbuilder.worldbuilder_suite.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ImageGeneratorService {
    private final RestTemplate restTemplate;
    private final String openAiApiKey;

    public ImageGeneratorService(@Value("${openai.api-key}") String openAiApiKey) {
        this.restTemplate = new RestTemplate();
        this.openAiApiKey = openAiApiKey;
    }

    public String generateImage(String description) {
        try {
            String url = "https://api.openai.com/v1/images/generations";
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + openAiApiKey);
            headers.set("Content-Type", "application/json");
            description = "female elven archer";

            String body = "{"
                    + "\"model\": \"dall-e-3\","
                    + "\"prompt\": \"" + description + "\","
                    + "\"n\": 1,"
                    + "\"size\": \"1024x1024\","
                    + "\"quality\": \"hd\","
                    + "\"response_format\": \"b64_json\""
                    + "}";
            HttpEntity<String> entity = new HttpEntity<>(body, headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                String base64Image = response.getBody().split("\"b64_json\": \"")[1].split("\"")[0];
                return "data:image/png;base64," + base64Image;
            } else {
                throw new RuntimeException("Failed to generate image: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error generating image: " + e.getMessage(), e);
        }
    }
}