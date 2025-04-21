package com.worldbuilder.worldbuilder_suite.service;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;

@Service
public class DocumentLoader {
    public List<Document> loadDocuments(String directoryPath) {
        return FileSystemDocumentLoader.loadDocuments(Path.of(directoryPath));
    }
}
