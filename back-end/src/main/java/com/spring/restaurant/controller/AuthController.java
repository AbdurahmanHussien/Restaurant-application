package com.spring.restaurant.controller;

import com.spring.restaurant.request.LoginRequest;
import com.spring.restaurant.request.RegisterRequest;
import com.spring.restaurant.response.AuthResponse;
import com.spring.restaurant.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authService;


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register( @Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.createUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login( @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.loginUser(request));
    }
}
