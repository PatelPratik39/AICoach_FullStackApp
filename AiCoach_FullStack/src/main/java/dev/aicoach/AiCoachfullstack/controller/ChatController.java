package dev.aicoach.AiCoachfullstack.controller;


import dev.aicoach.AiCoachfullstack.dto.ChatRequest;
import dev.aicoach.AiCoachfullstack.services.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")

public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

//    @PostMapping
//    public String getChatResponse(@RequestBody ChatRequest request) {
//        return chatService.generateResponse(request);
//    }

//    @PostMapping("/send")
//    public ResponseEntity<ChatResponse> sendMessage(@RequestBody ChatRequest chatRequest) {
//        ChatResponse response = chatService.processMessage(chatRequest);
//        return ResponseEntity.ok(response);
//    }

//    @GetMapping("/history")
//    public ResponseEntity<?> getChatHistory(@RequestParam(defaultValue = "10") int limit) {
//        return ResponseEntity.ok(chatService.getChatHistory(limit));
//    }
//
//    @DeleteMapping("/clear")
//    public ResponseEntity<String> clearChat() {
//        chatService.clearChatHistory();
//        return ResponseEntity.ok("Chat history cleared successfully");
//    }
}
