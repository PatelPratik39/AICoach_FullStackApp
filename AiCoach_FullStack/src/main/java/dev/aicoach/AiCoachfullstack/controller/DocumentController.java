package dev.aicoach.AiCoachfullstack.controller;


import dev.aicoach.AiCoachfullstack.services.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/documents")
@AllArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

//    public DocumentController(DocumentService documentService) {
//        this.documentService = documentService;
//    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String uploadMessage = documentService.storeFile(file);  //store the file
            // Process the file to store in vector database
            documentService.processFile(Path.of("src/main/resources/docs", file.getOriginalFilename()));

            return ResponseEntity.ok(Map.of(
                    "fileName", Objects.requireNonNull(file.getOriginalFilename()),
                    "status", "SUCCESS",
                    "message", "File uploaded successfully!",
                    "path", uploadMessage
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "fileName", Objects.requireNonNull(file.getOriginalFilename()),
                    "status", "FAILURE",
                    "message", e.getMessage()
            ));
        }
    }
}
