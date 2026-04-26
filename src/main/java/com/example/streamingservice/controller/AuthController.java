package com.example.streamingservice.controller;

import com.example.streamingservice.DTO.SignInRequest;
import com.example.streamingservice.DTO.SignUpRequest;
import com.example.streamingservice.JWT.JwtAuthenticationResponse;
import com.example.streamingservice.entity.UserEntity;
import com.example.streamingservice.repository.UserRepository;
import com.example.streamingservice.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signUp")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authService.signUp(request);
    }


    @PostMapping("/signIn")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authService.signIn(request);
    }

}
