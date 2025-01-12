package dev.aicoach.AiCoachfullstack.utility;

import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReaderUtil {

    private static final Logger log = LoggerFactory.getLogger(ExcelReaderUtil.class);

    public static String readExcel(File file) throws IOException {
        StringBuilder content = new StringBuilder();
//
//        try (Workbook workbook = WorkbookFactory.create(new FileInputStream(file))) {
//            // Process the workbook
//        } catch (IOException e) {
//            log.error("Error reading Excel file: {}", e.getMessage(), e);
//            throw new DocumentProcessingException("Failed to process Excel file.");
//        } catch (InvalidFormatException e) {
//            log.error("Invalid Excel file format: {}", e.getMessage(), e);
//            throw new DocumentProcessingException("Invalid Excel file format.");
//        }

//        try (FileInputStream fis = new FileInputStream(file);
//             Workbook workbook = WorkbookFactory.create(fis)) {
        try (Workbook workbook = WorkbookFactory.create(new FileInputStream(file))) {
            for (Sheet sheet : workbook) {
                for (Row row : sheet) {
                    for (Cell cell : row) {
                        content.append(cell.toString()).append(" ");
                    }
                    content.append("\n");
                }
            }
        }
        return content.toString();
    }
}
