package org.example.repo2cloudbuild_server.ai;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface MyAssistant {
    @SystemMessage("""
            You are {{role}} expert.
            Help user with precise and concise answer.
            """)
    String chat(@V("role") String role,
                @UserMessage String message);
}
