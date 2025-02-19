package com.ollama.backend.service;


import com.ollama.backend.entity.User;
import com.ollama.backend.repository.UserRepository;
import com.ollama.backend.specification.UserSearchSpecification;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class AIServiceImplement implements AIService{

    private ChatClient chatClient;

    @Autowired
    private UserRepository userRepository;

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

    @Override
    public Page<User> fetchUserDetails(String firstName, String lastName, String ssn, String userId, String email, Integer page) {
        Pageable pageable = PageRequest.of(page, 20, Sort.Direction.ASC);
        return userRepository.findAll(UserSearchSpecification.searchUser(firstName, lastName, ssn, userId, email),pageable);
    }
}
