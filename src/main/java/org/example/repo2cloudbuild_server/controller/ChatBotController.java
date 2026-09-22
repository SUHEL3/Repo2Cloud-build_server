package org.example.repo2cloudbuild_server.controller;

import dev.langchain4j.model.chat.ChatModel;
import lombok.RequiredArgsConstructor;
import org.example.repo2cloudbuild_server.ai.MyAssistant;
import org.example.repo2cloudbuild_server.dto.requestDTO.ChatBotMessageRequest;
import org.example.repo2cloudbuild_server.dto.requestDTO.RoleBasedChatBotRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bot")
@RequiredArgsConstructor
public class ChatBotController {
    private final ChatModel chatModel;
    private final MyAssistant assistant;

    @PostMapping()
    public String chat(
            @RequestBody ChatBotMessageRequest request
            ){
        return chatModel.chat(request.getMsg());
    }

    @PostMapping("/role")
    public String chat(
            @RequestBody RoleBasedChatBotRequest request
            ){
        return assistant.chat(request.getRole(),request.getMsg());
    }
}
