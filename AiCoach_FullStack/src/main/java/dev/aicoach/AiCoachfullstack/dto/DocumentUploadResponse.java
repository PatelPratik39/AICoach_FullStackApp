package dev.aicoach.AiCoachfullstack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DocumentUploadResponse {
    private String fileName;
    private String status;
    private String message;
}
