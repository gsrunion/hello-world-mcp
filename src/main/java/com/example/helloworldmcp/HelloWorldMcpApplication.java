package com.example.helloworldmcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class HelloWorldMcpApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldMcpApplication.class, args);
    }

}
