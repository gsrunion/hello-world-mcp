package com.example.helloworldmcp;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "delegate")
public class DelegateProperties {

    private Map<String, Provider> providers = new LinkedHashMap<>();
    private Map<String, Route> aliases = new LinkedHashMap<>();

    @Data
    public static class Provider {
        private String baseUrl;
        private String apiKey;
    }

    @Data
    public static class Route {
        private String provider;
        private String model;
    }
}
