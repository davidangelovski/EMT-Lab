package com.example.emtlab.model.dto;

import com.example.emtlab.model.domain.Author;

import java.util.List;

public record DisplayAuthorDto(
        Long id,
        String name,
        String surname,
        Long countryId,
        String countryName
) {
    public static DisplayAuthorDto from(Author author) {
        return new DisplayAuthorDto(
                author.getId(),
                author.getName(),
                author.getSurname(),
                author.getCountry().getId(),
                author.getCountry().getName()
        );
    }

    public static List<DisplayAuthorDto> from(List<Author> authors) {
        return authors.stream()
                .map(DisplayAuthorDto::from)
                .toList();
    }
}

