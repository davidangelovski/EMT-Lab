package com.example.emtlab.model.dto;

public record AuthResponseDto(
        String token,
        long expiresInMs
) {
}

