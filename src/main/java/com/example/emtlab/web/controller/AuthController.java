package com.example.emtlab.web.controller;

import com.example.emtlab.model.dto.AuthRequestDto;
import com.example.emtlab.model.dto.AuthResponseDto;
import com.example.emtlab.service.application.AuthApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthApplicationService authApplicationService;

    public AuthController(AuthApplicationService authApplicationService) {
        this.authApplicationService = authApplicationService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody AuthRequestDto authRequestDto) {
        return ResponseEntity.ok(authApplicationService.login(authRequestDto));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@Valid @RequestBody AuthRequestDto authRequestDto) {
        return ResponseEntity.ok(authApplicationService.register(authRequestDto));
    }
}

