package dev.aicoach.AiCoachfullstack.services;

import dev.aicoach.AiCoachfullstack.dto.PersonaRequest;
import dev.aicoach.AiCoachfullstack.entity.Persona;
import dev.aicoach.AiCoachfullstack.repository.PersonaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonaService {

    private final PersonaRepository personaRepository;

    public Persona addPersona(PersonaRequest request) {
        Persona persona = new Persona();
        persona.setName(request.getName());
        persona.setRole(request.getRole());
        return personaRepository.save(persona);
    }
}
