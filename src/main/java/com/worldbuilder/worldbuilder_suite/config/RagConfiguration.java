package com.worldbuilder.worldbuilder_suite.config;

import com.worldbuilder.worldbuilder_suite.service.DocumentLoader;
import com.worldbuilder.worldbuilder_suite.service.EmbeddingService;
import com.worldbuilder.worldbuilder_suite.service.LanguageModelService;
import com.worldbuilder.worldbuilder_suite.service.RagChain;
import com.worldbuilder.worldbuilder_suite.service.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RagConfiguration {

    @Value("${openai.api-key}")
    private String openAiApiKey;

    @Bean
    public DocumentLoader documentLoader() {
        return new DocumentLoader();
    }

    @Bean
    public EmbeddingService embeddingService() {
        return new EmbeddingService(openAiApiKey);
    }

    @Bean
    public VectorStore vectorStore(EmbeddingService embeddingService) {
        return new VectorStore(embeddingService.getEmbeddingModel());
    }

    @Bean
    public LanguageModelService languageModelService() {
        return new LanguageModelService(openAiApiKey);
    }

    @Bean
    public RagChain ragChain(LanguageModelService languageModelService, EmbeddingService embeddingService, VectorStore vectorStore) {
        return new RagChain(
                languageModelService.getChatLanguageModel(),
                embeddingService.getEmbeddingModel(),
                vectorStore.getEmbeddingStore()
        );
    }


}
