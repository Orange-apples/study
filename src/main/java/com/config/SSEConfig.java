package com.config;

import com.service.SSEComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class SSEConfig {
    @Autowired
    SSEComponent sseComponent;

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return (RouterFunction<ServerResponse>) RouterFunctions.route(GET("/time"), req -> sseComponent.getTime());
    }
}
