package com.example.helloworldmcp;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DelegateService {

    private final DelegateProperties properties;
    private final ConcurrentMap<String, ChatClient> clientsByAlias = new ConcurrentHashMap<>();

    public String delegate(String alias, String prompt) {
        return clientsByAlias.computeIfAbsent(alias, this::createClient)
                .prompt()
                .user(prompt)
                .call()
                .content();
    }

    private ChatClient createClient(String alias) {
        DelegateProperties.Route route = properties.getAliases().get(alias);
        if (route == null) {
            throw new IllegalArgumentException("Unknown model alias '%s'. Configured aliases: %s"
                    .formatted(alias, properties.getAliases().keySet()));
        }

        DelegateProperties.Provider provider = properties.getProviders().get(route.getProvider());
        if (provider == null) {
            throw new IllegalStateException("Alias '%s' refers to unknown provider '%s'"
                    .formatted(alias, route.getProvider()));
        }

        OpenAiChatModel model = OpenAiChatModel.builder()
                .options(OpenAiChatOptions.builder()
                        .baseUrl(provider.getBaseUrl())
                        .apiKey(provider.getApiKey())
                        .model(route.getModel())
                        .build())
                .build();
        return ChatClient.create(model);
    }
}
