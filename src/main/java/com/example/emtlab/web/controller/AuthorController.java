package com.example.emtlab.web.controller;

import com.example.emtlab.model.dto.DisplayAuthorDto;
import com.example.emtlab.service.application.AuthorApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorApplicationService authorApplicationService;

    public AuthorController(AuthorApplicationService authorApplicationService) {
        this.authorApplicationService = authorApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<DisplayAuthorDto>> findAll() {
        return ResponseEntity.ok(authorApplicationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayAuthorDto> findById(@PathVariable Long id) {
        return authorApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

