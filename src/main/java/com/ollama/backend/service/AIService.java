package com.ollama.backend.service;

import com.ollama.backend.entity.User;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Flux;

public interface AIService {

    String askAI(String question);

    Flux<String> askAIStream(String question);

    Page<User> fetchUserDetails(String firstName, String lastName, String ssn, String userId, String email, Integer page);
}
