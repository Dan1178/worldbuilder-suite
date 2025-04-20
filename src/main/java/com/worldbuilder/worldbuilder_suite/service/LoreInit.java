package com.worldbuilder.worldbuilder_suite.service;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class LoreInit implements CommandLineRunner {
    private final EmbeddingStore<TextSegment> embeddingStore;
    private final EmbeddingModel embeddingModel;

    public LoreInit(EmbeddingStore<TextSegment> embeddingStore, EmbeddingModel embeddingModel) {
        this.embeddingStore = embeddingStore;
        this.embeddingModel = embeddingModel;
    }

    @Override
    public void run(String... args) throws Exception {
        List<String> loreEntries = Arrays.asList(
                "The Elven Kingdom, founded 1,000 years ago by Queen Aeloria, thrives in the Emerald Forest. It is known for its archery and magical affinity.",
                "The Dwarven Clans of Ironhold are master blacksmiths, living in the Stoneheart Mountains. They have a longstanding rivalry with the Elven Kingdom.",
                "The Human Empire of Solara, established 500 years ago, is the largest kingdom in the realm, known for its trade and military might."
        );

        for (String lore : loreEntries) {
            TextSegment segment = TextSegment.from(lore);
            dev.langchain4j.data.embedding.Embedding embedding = embeddingModel.embed(segment).content();
            embeddingStore.add(embedding, segment);
        }
    }
}
