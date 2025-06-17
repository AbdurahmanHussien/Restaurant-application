package com.spring.restaurant.controller;
import com.spring.restaurant.response.UserData;
import com.spring.restaurant.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/{id}")
    public UserData getUserData( @PathVariable long id) {
        return userService.getUserData(id);
    }


    @PutMapping("/{id}")
    public UserData updateUserData(@PathVariable long id, @RequestBody UserData userData) {
        return userService.updateUserData(id, userData);
    }

}
