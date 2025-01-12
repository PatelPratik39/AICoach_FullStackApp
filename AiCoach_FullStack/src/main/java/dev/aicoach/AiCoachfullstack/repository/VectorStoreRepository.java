package dev.aicoach.AiCoachfullstack.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VectorStoreRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveVectorWithHash(String fileHash, String vectorData) {
        String query = "INSERT INTO vector_store (file_hash, vector_data) VALUES (?, ?)";
        jdbcTemplate.update(query, fileHash, vectorData);
    }
}
