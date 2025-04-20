package com.worldbuilder.worldbuilder_suite.service;

import dev.langchain4j.chain.ConversationalRetrievalChain;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.huggingface.HuggingFaceChatModel;
import dev.langchain4j.model.huggingface.HuggingFaceEmbeddingModel;
import dev.langchain4j.retriever.EmbeddingStoreRetriever;
import dev.langchain4j.retriever.Retriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class LoremasterService {
    private final ConversationalRetrievalChain chain;

    public LoremasterService(@Value("${langchain4j.hugging-face.api-key}") String apiKey) {
        EmbeddingModel embeddingModel = HuggingFaceEmbeddingModel.builder()
                .accessToken(apiKey)
                .modelId("sentence-transformers/all-MiniLM-L6-v2")
                .build();

        EmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        ChatLanguageModel chatModel = HuggingFaceChatModel.builder()
                .accessToken(apiKey)
                .modelId("mistralai/Mixtral-8x7B-Instruct-v0.1")
                .build();

        // Create Retriever
        Retriever<TextSegment> retriever = new EmbeddingStoreRetriever(embeddingStore, embeddingModel, 3, 0.6);

        // RAG Chain
        this.chain = ConversationalRetrievalChain.builder()
                .chatLanguageModel(chatModel)
                .retriever(retriever)
                .build();
    }

    @Bean
    public EmbeddingModel embeddingModel(@Value("${langchain4j.hugging-face.api-key}") String apiKey) {
        return HuggingFaceEmbeddingModel.builder()
                .accessToken(apiKey)
                .modelId("sentence-transformers/all-MiniLM-L6-v2")
                .build();
    }

    @Bean
    public EmbeddingStore<TextSegment> embeddingStore() {
        return new InMemoryEmbeddingStore<>();
    }

    public String answerQuestion(String question) {
        return chain.execute(question);
    }
}