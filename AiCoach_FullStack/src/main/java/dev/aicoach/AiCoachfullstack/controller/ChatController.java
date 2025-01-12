package dev.aicoach.AiCoachfullstack.controller;


import dev.aicoach.AiCoachfullstack.dto.ChatRequest;
import dev.aicoach.AiCoachfullstack.services.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")

public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public String getChatResponse(@RequestBody ChatRequest request) {
        return chatService.generateResponse(request);
    }
}
