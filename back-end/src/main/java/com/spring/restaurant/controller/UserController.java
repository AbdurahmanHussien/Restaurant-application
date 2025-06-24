package com.spring.restaurant.controller;
import com.spring.restaurant.response.UserData;
import com.spring.restaurant.service.impl.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/{id}")
    @Operation(summary = "get user data")
    public UserData getUserData(@PathVariable long id) {
        return userService.getUserData(id);
    }


    @PutMapping("/{id}")
    @Operation(summary = "update user data")
    public UserData updateUserData(@PathVariable long id, @Valid @RequestBody UserData userData) {
        return userService.updateUserData(id, userData);
    }

}
