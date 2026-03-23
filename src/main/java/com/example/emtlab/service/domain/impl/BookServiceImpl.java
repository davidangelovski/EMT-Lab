package com.example.emtlab.service.domain.impl;

import com.example.emtlab.events.BookBorrowedEvent;
import com.example.emtlab.model.domain.Book;
import com.example.emtlab.model.enums.Category;
import com.example.emtlab.model.enums.State;
import com.example.emtlab.model.projection.BookDetailsProjection;
import com.example.emtlab.model.projection.BookShortProjection;
import com.example.emtlab.model.projection.BookStatsProjection;
import com.example.emtlab.model.projection.BookViewProjection;
import com.example.emtlab.repository.BookRepository;
import com.example.emtlab.repository.specification.BookSpecification;
import com.example.emtlab.service.domain.BookService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ApplicationEventPublisher publisher;

    public BookServiceImpl(BookRepository bookRepository, ApplicationEventPublisher publisher) {
        this.bookRepository = bookRepository;

        this.publisher = publisher;
    }
    @Override
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> update(Long id, Book book) {
        return bookRepository.findById(id)
                .map((existingBook) ->{
                    existingBook.setName(book.getName());
                    existingBook.setCategory(book.getCategory());
                    existingBook.setAuthor(book.getAuthor());
                    existingBook.setState(book.getState());
                    existingBook.setAvailableCopies(book.getAvailableCopies());
                    return bookRepository.save(existingBook);
                });
    }

    @Override
    public Optional<Book> borrowById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("No copies available");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        publisher.publishEvent(new BookBorrowedEvent(book));
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<Book> deleteById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        book.ifPresent(bookRepository::delete);
        return book;
    }

    @Override
    public Page<Book> findAllFiltered(Category category, State state, Long authorId, Boolean available, Pageable pageable) {
        return bookRepository.findAll(BookSpecification.filter(category, state, authorId, available), pageable);
    }

    @Override
    public List<BookShortProjection> findAllShort() {
        return bookRepository.findAllShort();
    }

    @Override
    public List<BookDetailsProjection> findAllDetailed() {
        return bookRepository.findAllDetailed();
    }

    @Override
    public List<BookViewProjection> getBookView() {
        return bookRepository.getBookView();
    }

    @Override
    public List<BookStatsProjection> getStats() {
        return bookRepository.getStats();
    }
}
