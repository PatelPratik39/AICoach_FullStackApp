package dev.aicoach.AiCoachfullstack.services;

import dev.aicoach.AiCoachfullstack.dto.DocumentUploadResponse;
import dev.aicoach.AiCoachfullstack.utility.CsvReaderUtil;
import dev.aicoach.AiCoachfullstack.utility.DocumentChunker;
import dev.aicoach.AiCoachfullstack.utility.ExcelReaderUtil;
import dev.aicoach.AiCoachfullstack.utility.PdfReaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class DocumentService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    private VectorStore vectorStore;

    private static final Logger logger = LoggerFactory.getLogger(DocumentService.class);

    public String storeFile(MultipartFile file) {
        try {
            // Validate file type
            validateFileType(file);

            // Save the file to the target directory
            Path targetLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(targetLocation);

            Path filePath = targetLocation.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath);

            // Process the file for vector database storage
            processFile(filePath);

            return "File uploaded successfully: " + filePath;
        } catch (IOException e) {
            logger.error("File storage failed: {}", e.getMessage());
            throw new RuntimeException("Could not store file. Error: " + e.getMessage(), e);
        }
    }

    public void processFile(Path filePath) {
        try {
            // Detect file type and process accordingly
            String content;
            if (filePath.toString().endsWith(".pdf")) {      //pdf file
                content = PdfReaderUtil.readPdf(filePath.toFile());
            } else if (filePath.toString().endsWith(".xlsx")) {      //xlsx file
                content = ExcelReaderUtil.readExcel(filePath.toFile());
            } else if (filePath.toString().endsWith(".xls")) {
                content = ExcelReaderUtil.readExcel(filePath.toFile());
            } else if (filePath.toString().endsWith(".csv")) {          //CSV file process
                content = CsvReaderUtil.readCsv(filePath.toFile());
            } else {
                throw new IllegalArgumentException("Unsupported file type: " + filePath);
            }

            // Chunk content
            List<String> chunks = DocumentChunker.chunkContent(content);

            // Convert chunks to Document objects and store in vector database
            for (String chunk : chunks) {
                // Create a Document object
                org.springframework.ai.document.Document document = new org.springframework.ai.document.Document(chunk);
                vectorStore.accept(List.of(document)); // Pass a list of Document objects
            }
        } catch (Exception e) {
            logger.error("Error processing file: {}", e.getMessage());
            throw new RuntimeException("Error processing file: " + filePath, e);
        }
    }

    private void validateFileType(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (fileName == null ||
                (!fileName.endsWith(".pdf") && !fileName.endsWith(".xlsx") && !fileName.endsWith(".csv") && !fileName.endsWith(".xls"))) {
            throw new IllegalArgumentException("Invalid file type: " + fileName);
        }
    }
}



//@Service
//public class DocumentService {
//
//    private static final Logger logger = LoggerFactory.getLogger(DocumentService.class);
//
//    @Value("${file.upload-dir}")
//    private String uploadDir;
//
//
//    private VectorStore vectorStore;
//
//    public String storeFile(MultipartFile file) {
//        try {
//            // Get the target location
//            Path targetLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
//            Files.createDirectories(targetLocation);
//
//            // Copy the file to the target location
//            Path filePath = targetLocation.resolve(file.getOriginalFilename());
//            Files.copy(file.getInputStream(), filePath);
//
//            // Process the file and store in vector database
//            processFile(filePath);
//
//            return "File uploaded successfully: " + filePath;
//        } catch (IOException e) {
//            throw new RuntimeException("Could not store file. Error: " + e.getMessage(), e);
//        }
//    }
//
//    public void processFile(Path filePath) {
//        try {
//            // Example for PDF processing
//            String content = PdfReaderUtil.readPdf(filePath.toFile());
//
//            // Split content into chunks
//            List<String> chunks = DocumentChunker.chunkContent(content);
//
//            // Store chunks in vector database
//            for (String chunk : chunks) {
//                vectorStore.accept(chunk);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("Error processing file: " + filePath, e);
//        }
//    }
//}



//    public DocumentUploadResponse uploadDocument(MultipartFile file) {
//
//        try {
//            String uploadDir = "src/main/resources/docs/";
//            File destinationFile = new File(uploadDir + file.getOriginalFilename());
//            file.transferTo(destinationFile);
//            return new DocumentUploadResponse(file.getOriginalFilename(), "SUCCESS", "File uploaded successfully.");
//        } catch (IOException ex) {
//            return new DocumentUploadResponse(file.getOriginalFilename(), "FAILURE", "File upload failed: " + ex.getMessage());
//        }
//    }
//}
