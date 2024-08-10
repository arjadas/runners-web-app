package com.example.app;

import org.springframework.stereotype.Component;

@Component  // @component means that this class is available to spring
public class WelcomeMessage {

    public String getWelcomeMessage() {
        return "Welcome to the Spring Boot Application!";
    }

}
