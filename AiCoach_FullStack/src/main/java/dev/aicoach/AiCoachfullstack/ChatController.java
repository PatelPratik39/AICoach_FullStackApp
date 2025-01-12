package dev.aicoach.AiCoachfullstack;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.PgVectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final PgVectorStore vectorStore;
    private ChatClient chatClient;


    public ChatController(ChatClient.Builder builder, PgVectorStore vectorStore) {
        this.chatClient = builder
                .defaultAdvisors(new QuestionAnswerAdvisor(vectorStore))
                .build();
        this.vectorStore = vectorStore;
    }


    @GetMapping("/")
    public String chat() {
        return chatClient.prompt()
                .user("what are the core topic of AWS Solution Architect -associate?")
                .call()
                .content();
    }


}
