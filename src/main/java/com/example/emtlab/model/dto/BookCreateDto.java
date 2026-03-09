package com.example.emtlab.model.dto;

import com.example.emtlab.model.domain.Author;
import com.example.emtlab.model.domain.Book;
import com.example.emtlab.model.enums.Category;
import com.example.emtlab.model.enums.State;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookCreateDto(
        @NotBlank
        String name,
        @NotNull
        Category category,
        @NotNull
        Long authorId,
        @NotNull
        State state,
        @Min(0)
        Integer availableCopies
)
{
    public Book toBook(Author author) {
        return new Book(name, category, author, state, availableCopies);
    }
}
