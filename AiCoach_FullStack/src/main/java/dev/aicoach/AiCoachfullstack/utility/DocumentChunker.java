package dev.aicoach.AiCoachfullstack.utility;

import java.util.ArrayList;
import java.util.List;

public class DocumentChunker {
    private static final int CHUNK_SIZE = 500;

    public static List<String> chunkContent(String content) {
        List<String> chunks = new ArrayList<>();

        int contentLength = content.length();
        for (int i = 0; i < contentLength; i += CHUNK_SIZE) {
            int end = Math.min(contentLength, i + CHUNK_SIZE);
            chunks.add(content.substring(i, end));
        }

        return chunks;
    }
}
