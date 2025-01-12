package dev.aicoach.AiCoachfullstack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAiConfig {
    // Placeholder for OpenAI API configuration
    @Bean
    public String openAiApiKey() {
        return "${OPENAI_API_KEY}";
    }
}
