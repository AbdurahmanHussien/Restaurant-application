package com.spring.restaurant.controller;
import com.spring.restaurant.dto.ContactInfoDto;
import com.spring.restaurant.service.auth.CustomUserDetails;
import com.spring.restaurant.service.impl.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/contact")
public class ContactInfoController {

        private final ContactService contactService;

        public ContactInfoController(ContactService contactService) {
            this.contactService = contactService;
        }


    @PostMapping
    @Operation(summary = "add  contact Info")
    public ResponseEntity<ContactInfoDto> addContactInfo(@Valid @RequestBody ContactInfoDto contactInfoDto, @AuthenticationPrincipal UserDetails user ) {
            Long userId = ((CustomUserDetails) user).getId();
            contactInfoDto.setUserId(userId);
            return ResponseEntity.ok(contactService.addInfo(contactInfoDto));
    }
}
