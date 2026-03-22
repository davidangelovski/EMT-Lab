package com.example.emtlab.service.domain.impl;

import com.example.emtlab.model.domain.Author;
import com.example.emtlab.repository.AuthorRepository;
import com.example.emtlab.service.domain.AuthorService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }
}
