package com.buddyNetwork.BuddyNetwork.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.buddyNetwork.BuddyNetwork.component.SocketConnectionHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new SocketConnectionHandler(), "/hello").setAllowedOrigins("*");
    }

    // @Override
    // public void configureMessageBroker(MessageBrokerRegistry registry) {
    // registry.setApplicationDestinationPrefixes("/app");
    // registry.enableSimpleBroker("/topic");
    // }
}
