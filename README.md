# Hello World MCP

A minimal Spring Boot MCP server with one tool: `hello`. Calling it returns `Hello, world!`.

## Requirements

- Java 25+

## Run

```bash
./gradlew bootRun
```

The stateless Streamable HTTP MCP endpoint is available at `http://localhost:8080/mcp`.

## MCP client configuration

Configure your MCP client to connect to `http://localhost:8080/mcp` using the stateless Streamable HTTP transport. The server exposes `hello` and `delegate` tools.

It also exposes a `delegate` tool. Pass an alias such as `cloud-general` and a prompt; the server looks up the alias, selects its provider URL and model, then returns the response. The included examples define `openai-general` and `cloud-general` aliases. Set the values for the providers you plan to use before starting the server:

```bash
export OPENAI_BASE_URL="https://api.openai.com"
export OPENAI_API_KEY="your-api-key"
export OPENAI_MODEL="your-openai-model"
export EXAMPLE_CLOUD_BASE_URL="https://your-provider.example.com"
export EXAMPLE_CLOUD_API_KEY="your-provider-api-key"
export EXAMPLE_CLOUD_MODEL="your-provider-model"
./gradlew bootRun
```

Add providers under `delegate.providers.<provider-name>` and aliases under `delegate.aliases.<alias-name>` in `application.properties`. Each alias has a `provider` key and `model` value, so multiple aliases can point to different models at the same provider URL. Provider endpoints must implement the OpenAI chat completions API. The sample values in `application.properties` are placeholders.

## Build

```bash
./gradlew build
```
