package com.example.emtlab.service.application.impl;

import com.example.emtlab.model.dto.DisplayAuthorDto;
import com.example.emtlab.service.application.AuthorApplicationService;
import com.example.emtlab.service.domain.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {

    private final AuthorService authorService;

    AuthorApplicationServiceImpl(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public Optional<DisplayAuthorDto> findById(Long id) {
        return authorService.findById(id)
                .map(DisplayAuthorDto::from);
    }

    @Override
    public List<DisplayAuthorDto> findAll() {
        return DisplayAuthorDto.from(authorService.findAll());
    }
}

