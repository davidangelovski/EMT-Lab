package com.example.emtlab.service.application;

import com.example.emtlab.model.domain.Book;
import com.example.emtlab.model.dto.CreateBookDto;
import com.example.emtlab.model.dto.DisplayBookDto;
import com.example.emtlab.model.enums.Category;
import com.example.emtlab.model.enums.State;
import com.example.emtlab.model.projection.BookDetailsProjection;
import com.example.emtlab.model.projection.BookShortProjection;
import com.example.emtlab.model.projection.BookStatsProjection;
import com.example.emtlab.model.projection.BookViewProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    Page<DisplayBookDto> findAllFiltered(
            Category category,
            State state,
            Long authorId,
            Boolean available,
            Pageable pageable
    );
    List<BookShortProjection> findAllShort();
    List<BookDetailsProjection> findAllDetailed();
    List<BookViewProjection> getBookView();
    List<BookStatsProjection> getStats();


}
