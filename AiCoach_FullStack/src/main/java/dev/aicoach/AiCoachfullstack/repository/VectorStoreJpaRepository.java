package dev.aicoach.AiCoachfullstack.repository;

import dev.aicoach.AiCoachfullstack.entity.VectorStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VectorStoreJpaRepository extends JpaRepository<VectorStoreEntity, Long> {
    boolean existsByFileHash(String fileHash);
}

