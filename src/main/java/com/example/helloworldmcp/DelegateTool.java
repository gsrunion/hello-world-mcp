package com.example.helloworldmcp;

import java.util.Arrays;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DelegateTool {

    private final ObjectProvider<SyncMcpToolCallbackProvider> remoteToolProvider;

    @McpTool(name = "delegate", description = "Invoke a tool exposed by a configured remote MCP server")
    public String delegate(
            @McpToolParam(description = "The remote tool name as exposed by the MCP client, including any server prefix", required = true) String toolName,
            @McpToolParam(description = "Arguments for the remote tool as a JSON object string", required = true) String argumentsJson) {
        SyncMcpToolCallbackProvider provider = remoteToolProvider.getIfAvailable();
        if (provider == null) {
            throw new IllegalStateException("No remote MCP connections are configured");
        }

        ToolCallback callback = Arrays.stream(provider.getToolCallbacks())
                .filter(tool -> tool.getToolDefinition().name().equals(toolName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Unknown remote tool '%s'. Available tools: %s".formatted(toolName, availableTools(provider))));

        return callback.call(argumentsJson);
    }

    private String availableTools(SyncMcpToolCallbackProvider provider) {
        return Arrays.stream(provider.getToolCallbacks())
                .map(tool -> tool.getToolDefinition().name())
                .collect(Collectors.joining(", "));
    }
}
