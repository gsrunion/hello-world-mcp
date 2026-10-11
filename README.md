# Hello World MCP

A minimal Spring Boot MCP server with one tool: `hello`. Calling it returns `Hello, world!`.

## Requirements

- Java 25+

## Run

```bash
./gradlew bootRun
```

The Streamable HTTP MCP endpoint is available at `http://localhost:8080/mcp`.

## MCP client configuration

Configure your MCP client to connect to `http://localhost:8080/mcp` using the Streamable HTTP transport. The server exposes one tool named `hello` and requires no arguments.

## Build

```bash
./gradlew build
```
