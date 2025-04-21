package com.worldbuilder.worldbuilder_suite.service;

import dev.langchain4j.chain.ConversationalRetrievalChain;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.retriever.EmbeddingStoreRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;

public class RagChain {

    private final ConversationalRetrievalChain chain;

    public RagChain(ChatLanguageModel chatLanguageModel, EmbeddingModel embeddingModel, EmbeddingStore<TextSegment> embeddingStore) {
        EmbeddingStoreRetriever retriever = EmbeddingStoreRetriever.from(embeddingStore, embeddingModel, 2);
        this.chain = ConversationalRetrievalChain.builder()
                .chatLanguageModel(chatLanguageModel)
                .retriever(retriever)
                .build();
    }

    public String ask(String question) {
        return chain.execute(question);
    }
}