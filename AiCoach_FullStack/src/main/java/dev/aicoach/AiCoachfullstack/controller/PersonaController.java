package dev.aicoach.AiCoachfullstack.controller;

import dev.aicoach.AiCoachfullstack.dto.PersonaRequest;
import dev.aicoach.AiCoachfullstack.entity.Persona;
import dev.aicoach.AiCoachfullstack.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    private final PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping
    public Persona addPersona(@RequestBody PersonaRequest request) {
        return personaService.addPersona(request);
    }
}
