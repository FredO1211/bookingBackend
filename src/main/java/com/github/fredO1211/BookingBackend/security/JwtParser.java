package com.github.fredO1211.BookingBackend.security;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;


public class JwtParser {
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    public String parse(HttpServletRequest request) {
        String headerAuth = request.getHeader(AUTHORIZATION);
        if (isValidBearerToken(headerAuth)) {
            return headerAuth.substring(7);
        }
        return null;
    }

    private boolean isValidBearerToken(String headerAuth) {
        return StringUtils.hasText(headerAuth) && headerAuth.startsWith(BEARER);
    }
}
