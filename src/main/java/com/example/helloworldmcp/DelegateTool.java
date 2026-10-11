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
    private final DelegateProperties delegateProperties;

    @McpTool(name = "delegate", description = "Assign work to a configured remote agent by invoking its MCP task tool")
    public String delegate(
            @McpToolParam(description = "Configured agent alias, for example repo-agent", required = true) String agent,
            @McpToolParam(description = "Structured arguments for the agent's remote task tool, as a JSON object string", required = true) String argumentsJson) {
        SyncMcpToolCallbackProvider provider = remoteToolProvider.getIfAvailable();
        if (provider == null) {
            throw new IllegalStateException("No remote MCP connections are configured");
        }

        String toolName = delegateProperties.getAgents().getOrDefault(agent, "");
        if (toolName.isBlank()) {
            throw new IllegalArgumentException("Unknown agent '%s'. Configured agents: %s"
                    .formatted(agent, delegateProperties.getAgents().keySet()));
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
