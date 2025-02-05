package com.ollama.backend.service;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class AIServiceImplement implements AIService{

    private ChatClient chatClient;

    public AIServiceImplement(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }
    @Override
    public String askAI(String question) {
        return chatClient.prompt(question).call().content();
    }

    @Override
    public Flux<String> askAIStream(String question) {
        return chatClient.prompt()
                .user(question)
                .stream()
                .content();
    }
}
