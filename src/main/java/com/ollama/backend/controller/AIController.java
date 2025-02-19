package com.ollama.backend.controller;

import com.ollama.backend.entity.User;
import com.ollama.backend.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    @Autowired
    public AIService aiService;

    @GetMapping("/deep-seek/basic")
    public ResponseEntity<String> askAiQuestion(@RequestParam("question") String question) {
        String response = aiService.askAI(question);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/deep-seek/streaming")
    public ResponseEntity<Flux<String>> askAiQuestionStream(@RequestParam("question") String question) {
        Flux<String> response = aiService.askAIStream(question);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user_search")
    public Page<User> getUserDetails(@RequestParam(value = "firstName", required = false) String firstName,
                                     @RequestParam(value = "lastName", required = false) String lastName,
                                     @RequestParam(value = "ssn", required = false) String ssn,
                                     @RequestParam(value = "userId", required = false) String userId,
                                     @RequestParam(value = "email", required = false) String email,
                                     @RequestParam(value = "page", defaultValue = "0") Integer page) {
        return aiService.fetchUserDetails(firstName, lastName, ssn, userId, email, page);
    }
}
