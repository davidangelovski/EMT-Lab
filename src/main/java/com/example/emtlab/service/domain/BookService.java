package com.example.emtlab.service.domain;

import com.example.emtlab.model.domain.Book;
import com.example.emtlab.model.dto.DisplayBookDto;
import com.example.emtlab.model.enums.Category;
import com.example.emtlab.model.enums.State;
import com.example.emtlab.model.projection.BookDetailsProjection;
import com.example.emtlab.model.projection.BookShortProjection;
import com.example.emtlab.model.projection.BookStatsProjection;
import com.example.emtlab.model.projection.BookViewProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> findById(Long id);
    List<Book> findAll();
    Book create(Book book);
    Optional<Book> update(Long id, Book book);
    Optional<Book> borrowById(Long id);
    Optional<Book> deleteById(Long id);
    Page<Book> findAllFiltered(
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