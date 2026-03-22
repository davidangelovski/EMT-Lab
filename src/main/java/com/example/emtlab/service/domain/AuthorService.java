package com.example.emtlab.service.domain;

import com.example.emtlab.model.domain.Author;

import java.util.Optional;

public interface AuthorService {
    Optional<Author> findById(Long id);
}
