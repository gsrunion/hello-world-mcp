package com.example.helloworldmcp;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DelegateTool {

    private final ChatClient chatClient;

    @McpTool(name = "delegate", description = "Send a prompt to the configured OpenAI-compatible model and return its response")
    public String delegate(
            @McpToolParam(description = "The prompt to send to the configured model", required = true) String prompt) {
        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}
