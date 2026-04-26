package com.example.emtlab.service.application;

import com.example.emtlab.model.dto.DisplayAuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorApplicationService {
    Optional<DisplayAuthorDto> findById(Long id);
    List<DisplayAuthorDto> findAll();
}

