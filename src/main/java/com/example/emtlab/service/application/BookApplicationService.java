package com.example.emtlab.service.application;

import com.example.emtlab.model.domain.Book;
import com.example.emtlab.model.dto.CreateBookDto;
import com.example.emtlab.model.dto.DisplayBookDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
public interface BookApplicationService {
    Optional<DisplayBookDto> findById(Long id);
    List<DisplayBookDto> findAll();
    DisplayBookDto create(CreateBookDto createBookDto);
    Optional<DisplayBookDto> update(Long id, CreateBookDto createBookDto);
    Optional<DisplayBookDto> borrowById(Long id);
    Optional<DisplayBookDto> deleteById(Long id);
}
