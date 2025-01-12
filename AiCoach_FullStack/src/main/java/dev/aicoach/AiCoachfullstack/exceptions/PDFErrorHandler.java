package dev.aicoach.AiCoachfullstack.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

public class PDFErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(PDFErrorHandler.class);

    public static void handleError(Resource pdfResource, Exception e) {
        log.error("Error processing file: {}. Reason: {}", pdfResource.getFilename(), e.getMessage(), e);
        // Optional: We can throw a custom exception
        throw new RuntimeException("Failed to process PDF: " + pdfResource.getFilename(), e);
    }
}

