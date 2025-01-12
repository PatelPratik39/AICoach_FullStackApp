package dev.aicoach.AiCoachfullstack.services;

import dev.aicoach.AiCoachfullstack.dto.ChatRequest;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    public String generateResponse(ChatRequest request) {
        // Mock response logic based on persona
        if ("Executive Sponsor".equalsIgnoreCase(request.getPersona())) {
            return "As an Executive Sponsor, here’s the high-level overview.";
        } else if ("Implementation Lead".equalsIgnoreCase(request.getPersona())) {
            return "As an Implementation Lead, here are the steps to proceed.";
        }
        return "Default response based on your input.";
    }
}
