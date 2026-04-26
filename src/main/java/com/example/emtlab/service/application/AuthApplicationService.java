package com.example.emtlab.service.application;

import com.example.emtlab.model.dto.AuthRequestDto;
import com.example.emtlab.model.dto.AuthResponseDto;

public interface AuthApplicationService {
    AuthResponseDto login(AuthRequestDto authRequestDto);
    AuthResponseDto register(AuthRequestDto authRequestDto);
}

