package com.example.emtlab.service.domain;

import com.example.emtlab.model.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> findById(Long id);
    List<Book> findAll();
    Book create(Book book);
    Optional<Book> update(Long id, Book book);
    Optional<Book> borrowById(Long id);
    Optional<Book> deleteById(Long id);
}