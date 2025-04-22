package com.worldbuilder.worldbuilder_suite.service;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
public class VectorStore {

    private final EmbeddingStore<TextSegment> embeddingStore;
    private final EmbeddingModel embeddingModel;

    public VectorStore(EmbeddingModel embeddingModel) {
        this.embeddingStore = new InMemoryEmbeddingStore<>();
        this.embeddingModel = embeddingModel;
    }

    public void addDocuments(List<Document> documents) {
        for (Document document : documents) {
            String text = document.text();
            TextSegment segment = TextSegment.from(text);
            Embedding embedding = embeddingModel.embed(segment).content();
            embeddingStore.add(embedding, segment);
        }
    }

    public boolean clearAllDocuments() {
        try {
            getEmbeddingStore().removeAll();
            return true;
        } catch (Exception e) {
            log.error("Problem while removing all documents from vector store: ", e);
            return false;
        }
    }

}