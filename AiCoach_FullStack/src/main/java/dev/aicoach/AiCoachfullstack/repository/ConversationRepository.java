package dev.aicoach.AiCoachfullstack.repository;

import dev.aicoach.AiCoachfullstack.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
}
