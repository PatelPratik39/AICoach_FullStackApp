package dev.aicoach.AiCoachfullstack.repository;

import dev.aicoach.AiCoachfullstack.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
