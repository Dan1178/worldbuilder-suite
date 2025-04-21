package com.worldbuilder.worldbuilder_suite.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Service
public class FileUploadService {

    public void addUserLore(String text) throws IOException {
        Path userTextFilesDir = Paths.get("userTextFiles");
        if (!Files.exists(userTextFilesDir)) {
            Files.createDirectory(userTextFilesDir);
            log.info("Created directory: {}", userTextFilesDir.toAbsolutePath());
        }

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String uniqueId = UUID.randomUUID().toString();
        String filename = "lore_" + timestamp + "_" + uniqueId + ".txt";
        Path filePath = userTextFilesDir.resolve(filename);

        Files.writeString(filePath, text);
        log.info("Saved user lore to file: {}", filePath.toAbsolutePath());

        // Embed the text and add to EmbeddingStore
//        TextSegment segment = TextSegment.from(text);
//        dev.langchain4j.data.embedding.Embedding embedding = embeddingModel.embed(segment).content();
//        String id = embeddingStore.add(embedding, segment);
//        log.info("Added user lore to EmbeddingStore with ID: {}", id);
    }

}
