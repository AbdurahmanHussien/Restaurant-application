package com.spring.restaurant.controller;
import com.spring.restaurant.dto.ContactInfoDto;
import com.spring.restaurant.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/contact")
public class ContactInfoController {

        private final ContactService contactService;

        public ContactInfoController(ContactService contactService) {
            this.contactService = contactService;
        }


    @PostMapping
    @Operation(summary = "add  contact Info")
    public ResponseEntity<ContactInfoDto> addContactInfo( @Valid @RequestBody ContactInfoDto contactInfoDto) {
       return ResponseEntity.ok(contactService.addInfo(contactInfoDto));
    }
}
