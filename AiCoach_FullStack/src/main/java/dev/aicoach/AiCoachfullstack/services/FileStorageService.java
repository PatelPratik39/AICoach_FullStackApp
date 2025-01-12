//package dev.aicoach.AiCoachfullstack.services;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//@Service
//public class FileStorageService {
//
//    @Value("${file.upload-dir}")
//    private String uploadDir;
//
//    public Path getUploadPath() {
//        return Paths.get(uploadDir).toAbsolutePath().normalize();
//    }
//}
