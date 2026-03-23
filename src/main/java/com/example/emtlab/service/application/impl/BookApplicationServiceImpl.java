package com.example.emtlab.service.application.impl;

import com.example.emtlab.model.dto.CreateBookDto;
import com.example.emtlab.model.dto.DisplayBookDto;
import com.example.emtlab.model.enums.Category;
import com.example.emtlab.model.enums.State;
import com.example.emtlab.model.projection.BookDetailsProjection;
import com.example.emtlab.model.projection.BookShortProjection;
import com.example.emtlab.model.projection.BookStatsProjection;
import com.example.emtlab.model.projection.BookViewProjection;
import com.example.emtlab.service.application.BookApplicationService;
import com.example.emtlab.service.domain.AuthorService;
import com.example.emtlab.service.domain.BookService;
import com.example.emtlab.service.domain.impl.BookServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BookApplicationServiceImpl implements BookApplicationService {
    private final BookService bookService;
    private final AuthorService authorService;

    BookApplicationServiceImpl(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }
    @Override
    public Optional<DisplayBookDto> borrowById(Long id) {
        return this.bookService.borrowById(id).map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> findById(Long id) {
        return bookService.findById(id).map(DisplayBookDto::from);
    }

    @Override
    public List<DisplayBookDto> findAll() {
        return DisplayBookDto.from(bookService.findAll());
    }

    @Override
    public DisplayBookDto create(CreateBookDto createBookDto) {
        return DisplayBookDto.from(bookService.create(createBookDto.toBook(authorService.findById(createBookDto.authorId()).orElseThrow())));
    }

    @Override
    public Optional<DisplayBookDto> update(Long id, CreateBookDto createBookDto) {
        return bookService.update(id, createBookDto.toBook(authorService.findById(createBookDto.authorId()).orElseThrow())).map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> deleteById(Long id) {
        return bookService.deleteById(id).map(DisplayBookDto::from);
    }

    @Override
    public Page<DisplayBookDto> findAllFiltered(Category category, State state, Long authorId, Boolean available, Pageable pageable) {
        return bookService.findAllFiltered(category, state, authorId, available, pageable).map(DisplayBookDto::from);
    }

    @Override
    public List<BookShortProjection> findAllShort() {
        return bookService.findAllShort();
    }

    @Override
    public List<BookDetailsProjection> findAllDetailed() {
        return bookService.findAllDetailed();
    }

    @Override
    public List<BookViewProjection> getBookView() {
        return bookService.getBookView();
    }

    @Override
    public List<BookStatsProjection> getStats() {
        return bookService.getStats();
    }
}
