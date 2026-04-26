package com.example.emtlab.service.application.impl;

import com.example.emtlab.model.dto.DisplayCountryDto;
import com.example.emtlab.service.application.CountryApplicationService;
import com.example.emtlab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {

    private final CountryService countryService;

    CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public Optional<DisplayCountryDto> findById(Long id) {
        return countryService.findById(id)
                .map(DisplayCountryDto::from);
    }

    @Override
    public List<DisplayCountryDto> findAll() {
        return DisplayCountryDto.from(countryService.findAll());
    }
}

