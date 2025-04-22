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
import java.util.stream.Stream;

@Slf4j
@Service
public class FileUploadService {

    private static final String USER_TXT_FILE_DIR = "userTextFiles";

    public void addUserLore(String text) throws IOException {
        Path userTextFilesDir = Paths.get(USER_TXT_FILE_DIR);
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
    }

    public boolean removeAllUserLore() {
        try {
            Path userTextFilesDir = Paths.get(USER_TXT_FILE_DIR);
            if (!Files.exists(userTextFilesDir) || !Files.isDirectory(userTextFilesDir)) {
                log.warn("Invalid directory path: " + USER_TXT_FILE_DIR);
                return false;
            }
            try (Stream<Path> files = Files.list(userTextFilesDir)) {
                files.filter(file -> Files.isRegularFile(file) && file.toString().toLowerCase().endsWith(".txt"))
                        .forEach(file -> {
                            try {
                                Files.delete(file);
                                log.info("Deleted file: {}", file.getFileName().toString());
                            } catch (IOException e) {
                                log.error("Error deleting file: {} - {}", file.getFileName().toString(), e.getMessage(), e);
                            }
                        });
            } catch (IOException e) {
                log.error("Error listing files in directory: {}", e.getMessage(), e);
            }
            return true;
        } catch (Exception e) {
            log.error("Problem while deleting text files from user text file directory: ", e);
            return false;
        }
    }

}
