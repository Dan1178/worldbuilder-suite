package com.worldbuilder.worldbuilder_suite.service;

import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import org.springframework.stereotype.Service;

@Service
public class EmbeddingService {

    private final EmbeddingModel embeddingModel;

    public EmbeddingService(String openAiApiKey) {
        this.embeddingModel = OpenAiEmbeddingModel.builder()
                .apiKey(openAiApiKey)
                .build();
    }

    public EmbeddingModel getEmbeddingModel() {
        return embeddingModel;
    }

}
