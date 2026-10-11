package com.example.helloworldmcp;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "delegate")
public class DelegateProperties {

    private Map<String, String> agents = new LinkedHashMap<>();
}
