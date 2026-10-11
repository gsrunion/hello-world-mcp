package com.example.helloworldmcp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.ai.mcp.annotation.McpTool;

@Slf4j
@Component
public class HelloTool {

    @McpTool(name = "hello", description = "Return a friendly hello world greeting")
    public String hello() {
        log.debug("Hello MCP tool invoked");
        return "Hello, world!";
    }
}
