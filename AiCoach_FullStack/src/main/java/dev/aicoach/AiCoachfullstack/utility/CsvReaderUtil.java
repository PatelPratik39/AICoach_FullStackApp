package dev.aicoach.AiCoachfullstack.utility;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class CsvReaderUtil {

        public static String readCsv(File filePath) {
            if (filePath == null || !filePath.exists() || !filePath.isFile() || !filePath.canRead()) {
                throw new IllegalArgumentException("Invalid file provided: " + (filePath != null ? filePath.getAbsolutePath() : "null"));
            }

            StringBuilder content = new StringBuilder();

//            try (FileInputStream fis = new FileInputStream(file);
//                 InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
//                 BufferedReader reader = new BufferedReader(isr);
//                 CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {
//
//                for (CSVRecord record : parser) {
//                    for (String cell : record) {
//                        content.append(cell).append(" ");
//                    }
//                    content.append("\n");
//                }
                try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
                     CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
                    csvParser.forEach(record -> {
                        System.out.println("Row: " + record.toString());
                    });

            } catch (IOException e) {
                throw new RuntimeException("Error reading CSV file: " + filePath.getAbsolutePath(), e);
            }

            return content.toString();
        }
}

//    public static String readCsv(File file) throws IOException {
//        StringBuilder content = new StringBuilder();
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
//            CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
//            for (CSVRecord record : parser) {
//                for (String cell : record) {
//                    content.append(cell).append(" ");
//                }
//                content.append("\n");
//            }
//        }
//        return content.toString();
//    }
//}

