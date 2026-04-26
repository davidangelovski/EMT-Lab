package com.example.emtlab.service.application;

import com.example.emtlab.model.dto.DisplayCountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    Optional<DisplayCountryDto> findById(Long id);
    List<DisplayCountryDto> findAll();
}

