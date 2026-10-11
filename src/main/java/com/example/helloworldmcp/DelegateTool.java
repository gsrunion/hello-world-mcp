package com.example.helloworldmcp;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DelegateTool {

    private final DelegateService delegateService;

    @McpTool(name = "delegate", description = "Send a prompt to a configured provider and model alias, then return its response")
    public String delegate(
            @McpToolParam(description = "Configured model alias, for example cloud-general", required = true) String alias,
            @McpToolParam(description = "The prompt to send to the selected model", required = true) String prompt) {
        return delegateService.delegate(alias, prompt);
    }
}
