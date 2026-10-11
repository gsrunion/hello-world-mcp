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

It also exposes a `delegate` tool that sends a prompt to the configured OpenAI-compatible chat endpoint and returns the model response. Set these environment variables before starting the server:

```bash
export OPENAI_BASE_URL="https://api.openai.com"
export OPENAI_API_KEY="your-api-key"
export OPENAI_MODEL="your-model-name"
./gradlew bootRun
```

For OpenAI-compatible providers, set `OPENAI_BASE_URL` to the provider's base URL and use the model name expected by that provider. The defaults in `application.properties` are placeholders.

## Build

```bash
./gradlew build
```
