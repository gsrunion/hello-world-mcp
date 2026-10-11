package com.example.helloworldmcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HelloWorldMcpApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldMcpApplication.class, args);
    }

    @Bean
    ChatClient chatClient(ChatClient.Builder builder) {
        return builder.build();
    }
}
