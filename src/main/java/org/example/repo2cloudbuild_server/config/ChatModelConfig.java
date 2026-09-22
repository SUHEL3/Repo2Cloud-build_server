package org.example.repo2cloudbuild_server.config;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.service.AiServices;
import org.example.repo2cloudbuild_server.ai.MyAssistant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class ChatModelConfig {

    @Bean
    public ChatModel chatModel(){
        return OllamaChatModel.builder()
                .baseUrl("http://localhost:11434")
                .modelName("qwen2.5-coder:1.5b")
                .timeout(Duration.ofMinutes(5))
                .maxRetries(2)
                .build();
    }

    @Bean
    public MyAssistant assistant(ChatModel chatModel){
        return AiServices.create(MyAssistant.class,chatModel);
    }
}
