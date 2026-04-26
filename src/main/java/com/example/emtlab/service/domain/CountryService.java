package com.example.emtlab.service.domain;

import com.example.emtlab.model.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    Optional<Country> findById(Long id);
    List<Country> findAll();
}

