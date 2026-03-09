package com.example.emtlab.model.dto;

import com.example.emtlab.model.domain.Book;
import com.example.emtlab.model.enums.Category;
import com.example.emtlab.model.enums.State;

import java.util.List;

public record DisplayBookDto(
        Long id,
        String name,
        Category category,
        String authorName,
        String authorSurname,
        String country,
        State state,
        Integer availableCopies
) {

    public static DisplayBookDto from(Book book) {
        return new DisplayBookDto(
                book.getId(),
                book.getName(),
                book.getCategory(),
                book.getAuthor().getName(),
                book.getAuthor().getSurname(),
                book.getAuthor().getCountry().getName(),
                book.getState(),
                book.getAvailableCopies()
        );
    }
    public static List<DisplayBookDto> from(List<Book> books) {
        return books.stream()
                .map(DisplayBookDto::from)
                .toList();
    }

    }