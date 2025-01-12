package dev.aicoach.AiCoachfullstack.utility;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReaderUtil {

    public static String readExcel(File file) throws IOException {
        StringBuilder content = new StringBuilder();
        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = WorkbookFactory.create(fis)) {

            for (Sheet sheet : workbook) {
                for (Row row : sheet) {
                    row.forEach(cell -> content.append(cell.toString()).append(" "));
                    content.append("\n");
                }
            }
        }
        return content.toString();
    }
}
