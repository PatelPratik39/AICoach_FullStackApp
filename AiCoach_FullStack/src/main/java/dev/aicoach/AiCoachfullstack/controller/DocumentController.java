package dev.aicoach.AiCoachfullstack.controller;


import dev.aicoach.AiCoachfullstack.exceptions.DuplicateFileException;
import dev.aicoach.AiCoachfullstack.services.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/documents")
@AllArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String uploadMessage = documentService.storeFile(file);  // store the file first

            return ResponseEntity.ok(Map.of(
                    "fileName", file.getOriginalFilename(),
                    "status", "SUCCESS",
                    "message", "File uploaded successfully!",
                    "path", uploadMessage
            ));
        } catch (DuplicateFileException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "fileName", file.getOriginalFilename(),
                    "status", "FAILURE",
                    "message", e.getMessage()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "fileName", file.getOriginalFilename(),
                    "status", "FAILURE",
                    "message", "An unexpected error occurred: " + e.getMessage()
            ));
        }
    }
}
