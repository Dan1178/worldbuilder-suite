package com.worldbuilder.worldbuilder_suite.controller;

import com.worldbuilder.worldbuilder_suite.service.DocumentLoader;
import com.worldbuilder.worldbuilder_suite.service.FileUploadService;
import com.worldbuilder.worldbuilder_suite.service.RagChain;
import com.worldbuilder.worldbuilder_suite.service.VectorStore;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@AllArgsConstructor
@RequestMapping("/api/loremaster")
@CrossOrigin(origins = "http://localhost:5173")
public class LoremasterController {
    private final FileUploadService fileUploadService;
    private final RagChain ragChain;
    private final DocumentLoader documentLoader;
    private final VectorStore vectorStore;

    private static final String USER_TXT_FILE_DIR = "userTextFiles";


    @PostMapping("/answer")
    public String answerQuestion(@RequestBody String question) {
        return ragChain.ask(question);
    }

    @PostMapping("/load-documents")
    public String loadDocuments() {
        Path userTextFilesDir = Paths.get(USER_TXT_FILE_DIR);
        var documents = documentLoader.loadDocuments(userTextFilesDir.toString());
        vectorStore.addDocuments(documents);
        return "Documents loaded successfully";
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadLore(@RequestBody String text) {
        try {
            fileUploadService.addUserLore(text);
            return ResponseEntity.ok("Lore uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading lore: " + e.getMessage());
        }
    }

    @DeleteMapping("/clear-all-documents")
    public void clearAllDocuments() {
        fileUploadService.removeAllUserLore();
        vectorStore.clearAllDocuments();
    }
}
