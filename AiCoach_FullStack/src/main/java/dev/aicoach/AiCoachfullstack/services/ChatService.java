package dev.aicoach.AiCoachfullstack.services;

import dev.aicoach.AiCoachfullstack.dto.ChatRequest;
import org.springframework.ai.chat.model.ChatResponse;

import java.util.*;

public interface ChatService {
    // Generate chat response
    String generateResponse(ChatRequest request);

    // Process a chat message
//    void processMessage(ChatRequest chatRequest);

    // Retrieve chat history with a limit
//    List<ChatResponse> getChatHistory(int limit);
//
//    // Clear chat history
//    void clearChatHistory();
}

//    private  final List<String> chatHistory = new ArrayList<>();
//
//    public ChatResponse processMessage(ChatRequest chatRequest) {
//        String userMessage = chatRequest.getMessage();
//        String botReply = "You said: " + userMessage;  // Replace this with your AI logic.
//
//        // Save the message and reply to history
//        chatHistory.add("User: " + userMessage);
//        chatHistory.add("Bot: " + botReply);
//
//        return new ChatResponse(botReply);
//    }
//
//    public List<String> getChatHistory(int limit) {
//        return chatHistory.subList(Math.max(chatHistory.size() - limit, 0), chatHistory.size());
//    }
//
//    public void clearChatHistory() {
//        chatHistory.clear();
//    }

//    public String generateResponse(ChatRequest request) {
//        // Mock response logic based on persona
//        if ("Executive Sponsor".equalsIgnoreCase(request.getPersona())) {
//            return "As an Executive Sponsor, here’s the high-level overview.";
//        } else if ("Implementation Lead".equalsIgnoreCase(request.getPersona())) {
//            return "As an Implementation Lead, here are the steps to proceed.";
//        }
//        return "Default response based on your input.";
//    }
//}
