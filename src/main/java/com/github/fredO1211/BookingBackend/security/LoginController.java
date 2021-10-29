package com.github.fredO1211.BookingBackend.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public LoginController(JwtUtils jwtUtils, AuthenticationManager authenticationManager, UserRepository repository, PasswordEncoder encoder) {
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity createAccount(@RequestBody LoginRequestDto loginRequestDto){
        repository.save(new User(loginRequestDto.getUsername(), encoder.encode(loginRequestDto.getPassword())));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok(jwtUtils.generateJwtToken(authentication));
    }
}
