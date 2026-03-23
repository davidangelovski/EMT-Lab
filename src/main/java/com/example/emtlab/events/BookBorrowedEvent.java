package com.example.emtlab.events;

import com.example.emtlab.model.domain.Book;

public record BookBorrowedEvent(Book book) {

    public Book getBook() {
        return book;
    }
}
