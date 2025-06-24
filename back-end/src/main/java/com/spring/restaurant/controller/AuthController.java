package com.spring.restaurant.controller;
import com.spring.restaurant.request.LoginRequest;
import com.spring.restaurant.request.RegisterRequest;
import com.spring.restaurant.response.AuthResponse;
import com.spring.restaurant.service.auth.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authService;


    @PostMapping("/signup")
    @Operation(summary = "signup for user")
    public ResponseEntity<AuthResponse> register( @Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.createUser(request));
    }

    @PostMapping("/login")
    @Operation(summary = "login for user")
    public ResponseEntity<AuthResponse> login( @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.loginUser(request));
    }

    @DeleteMapping("/remove/{id}")
    @Operation(summary = "Delete user by Id")
    public ResponseEntity<Void> removeUser(@PathVariable Long id) {
        authService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/reset-password")
    @Operation(summary = "reset password for user")
    public ResponseEntity<Map<String, String>> resetPassword(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        authService.resetPassword(email);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Password reset to Hello@1234");
        return ResponseEntity.ok(response);
    }
}
