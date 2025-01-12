package dev.aicoach.AiCoachfullstack.utility;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CsvReaderUtil {

    public static String readCsv(File file) throws IOException {
        StringBuilder content = new StringBuilder();
//        try (CSVParser parser = new CSVParser(new FileReader(file), CSVFormat.DEFAULT)) {
            try (CSVParser parser = CSVParser.parse(file, java.nio.charset.StandardCharsets.UTF_8, CSVFormat.DEFAULT)) {
            for (CSVRecord record : parser) {
                record.forEach(cell -> content.append(cell).append(" "));
                content.append("\n");
            }
        }
        return content.toString();
    }
}

