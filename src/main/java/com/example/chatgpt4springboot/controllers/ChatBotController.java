package com.example.chatgpt4springboot.controllers;

import com.example.chatgpt4springboot.dtos.ChatBotRequest;
import com.example.chatgpt4springboot.dtos.ChatBotResponse;
import com.example.chatgpt4springboot.dtos.Message;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ChatBotController {
    @Resource
    private RestTemplate restTemplate;

    @Value("${openAI.chatGPT.model}")
    private String model;

    @Value("${openAI.chatGPT.maxCompletions}")
    private int maxCompletions;

    @Value("${openAI.chatGPT.temperature}")
    private double temperature;

    @Value("${openAI.chatGPT.maxTokens}")
    private int maxTokens;

    @Value("${openAI.chatGPT.api.url}")
    private String apiUrl;

    @PostMapping("/chat")
    public ChatBotResponse chat(@RequestParam("prompt") String prompt) {
        ChatBotRequest request = new ChatBotRequest(model,
                List.of(new Message("user", prompt)),
                maxCompletions,
                temperature,
                maxTokens);
        return restTemplate.postForObject(apiUrl, request, ChatBotResponse.class);
    }
}
