package dev.aicoach.AiCoachfullstack.exceptions;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private Map<String, Object> createErrorResponse(String status, String error, String message, String details) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);
        response.put("error", error);
        response.put("message", message);
        if (details != null) {
            response.put("details", details);
        }
        return response;
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Map<String, Object>> handleMaxSizeException(MaxUploadSizeExceededException ex) {
        log.warn("File size exceeded the maximum limit: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                .body(createErrorResponse("FAILURE", "MaxUploadSizeExceeded",
                        "File size exceeds the maximum limit!", null));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundException ex) {
        log.warn("Resource not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(createErrorResponse("FAILURE", "ResourceNotFound", ex.getMessage(), null));
    }

    @ExceptionHandler(DocumentProcessingException.class)
    public ResponseEntity<Map<String, Object>> handleDocumentProcessingException(DocumentProcessingException ex) {
        log.error("Document processing error: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(createErrorResponse("FAILURE", "DocumentProcessingError",
                        "Error processing the document. Please try again later.", ex.getMessage()));
    }

    @ExceptionHandler(DuplicateFileException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateFileException(DuplicateFileException ex) {
        log.warn("Duplicate file detected: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(createErrorResponse("FAILURE", "DuplicateFileError",
                        ex.getMessage(), null));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("Invalid argument: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(createErrorResponse("FAILURE", "IllegalArgumentError",
                        ex.getMessage(), null));
    }

    @ExceptionHandler(ExceptionInInitializerError.class)
    public ResponseEntity<Map<String, Object>> handleInitializationError(ExceptionInInitializerError ex) {
        log.error("Initialization error: {}", ex.getMessage(), ex);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "FAILURE");
        response.put("error", "InitializationError");
        response.put("message", "An error occurred during library initialization. Please contact support.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(NoClassDefFoundError.class)
    public ResponseEntity<Map<String, Object>> handleNoClassDefFoundError(NoClassDefFoundError ex) {
        log.error("Missing class definition: {}", ex.getMessage(), ex);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "FAILURE");
        response.put("error", "DependencyError");
        response.put("message", "A required library or dependency is missing. Please contact support.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        log.error("Unexpected error occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(createErrorResponse("FAILURE", "InternalServerError",
                        "An unexpected error occurred. Please contact support.", ex.getMessage()));
    }
}

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleGenericException(Exception ex) {
//        log.error("An unexpected error occurred: {}", ex.getMessage(), ex);
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred. Please try again later.");
//    }
//}
