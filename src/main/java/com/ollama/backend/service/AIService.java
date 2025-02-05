package com.ollama.backend.service;

import reactor.core.publisher.Flux;

public interface AIService {

    String askAI(String question);

    Flux<String> askAIStream(String question);
}
