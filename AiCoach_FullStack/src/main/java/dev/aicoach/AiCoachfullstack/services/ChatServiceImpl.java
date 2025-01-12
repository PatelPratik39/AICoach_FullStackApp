package dev.aicoach.AiCoachfullstack.services;

import dev.aicoach.AiCoachfullstack.dto.ChatRequest;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatServiceImpl implements ChatService {

//    private final List<ChatResponse> chatHistory = new ArrayList<>();

    @Override
    public String generateResponse(ChatRequest request) {
        if ("Executive Sponsor".equalsIgnoreCase(request.getPersona())) {
            return "As an Executive Sponsor, here’s the high-level overview.";
        } else if ("Implementation Lead".equalsIgnoreCase(request.getPersona())) {
            return "As an Implementation Lead, here are the steps to proceed.";
        }
        return "Default response based on your input." + request.getMessage();
    }


//    @Override
//    public ChatResponse void processMessage(ChatRequest chatRequest) {
//        // Example list of string messages
//        List<String> messageContents = List.of("Response 1", "Response 2");
//
//        // Map strings to AssistantMessage
//        List<AssistantMessage> assistantMessages = messageContents.stream()
//                .map(AssistantMessage::new) // Assuming AssistantMessage(String content) constructor exists
//                .toList();
//
//        // Use the list of AssistantMessage
//        return new ChatResponse(assistantMessages);
//    }



//        ChatResponse response = new ChatResponse();
//        response.setMessage("Processed: " + chatRequest.getMessage());
//        response.setTimestamp(System.currentTimeMillis());
//        chatHistory.add(response);
//        return response;
//    }

//    @Override
//    public List<ChatResponse> getChatHistory(int limit) {
//        // Retrieve the last `limit` number of responses
//        return chatHistory.subList(Math.max(chatHistory.size() - limit, 0), chatHistory.size());
//    }
//
//    @Override
//    public void clearChatHistory() {
//        // Clear the chat history
//        chatHistory.clear();
//    }

}
