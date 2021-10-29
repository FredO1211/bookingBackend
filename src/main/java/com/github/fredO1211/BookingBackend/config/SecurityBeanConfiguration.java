package com.github.fredO1211.BookingBackend.config;

import com.github.fredO1211.BookingBackend.security.JwtParser;
import com.github.fredO1211.BookingBackend.security.SecurityContextUpdater;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SecurityBeanConfiguration {

    @Bean
    public JwtParser jwtParser(){
        return new JwtParser();
    }

    @Bean
    public SecurityContextUpdater securityContextUpdater() {
        return new SecurityContextUpdater();
    }
}
