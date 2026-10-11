# Hello World MCP

A minimal Spring Boot MCP server with a `hello` tool and a `delegate` tool for invoking tools on configured remote MCP servers.

## Requirements

- Java 25+

## Run

```bash
./gradlew bootRun
```

The stateless Streamable HTTP MCP endpoint is available at `http://localhost:8080/mcp`.

## MCP client configuration

Configure your MCP client to connect to `http://localhost:8080/mcp` using the stateless Streamable HTTP transport. The server exposes `hello` and `delegate` tools.

The `delegate` tool invokes a tool on a remote MCP server and returns its result. Add each remote Streamable HTTP MCP server to `application.properties`:

```properties
spring.ai.mcp.client.streamable-http.connections.filesystem.url=https://your-mcp-host.example.com
spring.ai.mcp.client.streamable-http.connections.filesystem.endpoint=/mcp
```

The connection key (`filesystem` above) is a local name for that remote server. The MCP client connects to configured servers at startup and discovers their tools. `delegate` takes the remote tool name as exposed by Spring AI and a JSON object string containing the remote tool's arguments. When names collide, Spring AI prefixes them; pass the full exposed name shown in the discovered tool list.

## Build

```bash
./gradlew build
```
