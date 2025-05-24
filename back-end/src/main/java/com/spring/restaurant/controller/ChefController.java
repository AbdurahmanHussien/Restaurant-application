package com.spring.restaurant.controller;

import com.spring.restaurant.dto.CategoryDto;
import com.spring.restaurant.dto.ChefDto;
import com.spring.restaurant.service.ChefService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/chef")
public class ChefController {

        private final ChefService chefService;

        public ChefController(ChefService chefService) {
            this.chefService = chefService;
        }


    @GetMapping
    @Operation(summary = "Get All chefs")
    public ResponseEntity<List<ChefDto>> getAllChefs() {
        List<ChefDto> chefs = chefService.getAllChefs();
        return ResponseEntity.ok(chefs);
    }
}
