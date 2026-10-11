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

The connection key (`filesystem` above) is a local name for that remote server. The MCP client connects to configured servers at startup and discovers their tools. Add agent aliases that map to the full remote tool names exposed by Spring AI:

```properties
delegate.agents.repo-agent=bot_fleet_dispatch_task
delegate.agents.docs-agent=docs_worker_dispatch_task
```

`delegate` takes an agent alias and a JSON object string containing that agent tool's arguments. For example, a bot fleet API could receive `{"task":"Update the parser","workspace":"/work/repo","constraints":"Keep the public API stable"}`. The remote agent server owns the workspace and its file tools; this MCP server only dispatches the request. When remote tool names collide, Spring AI prefixes them; map the alias to the full exposed name shown in the discovered tool list.

## Build

```bash
./gradlew build
```
