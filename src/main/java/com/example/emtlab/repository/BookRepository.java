package com.example.emtlab.repository;

import com.example.emtlab.model.domain.Book;
import com.example.emtlab.model.projection.BookDetailsProjection;
import com.example.emtlab.model.projection.BookShortProjection;
import com.example.emtlab.model.projection.BookStatsProjection;
import com.example.emtlab.model.projection.BookViewProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    @Query("""
    SELECT 
        b.id AS id,
        b.name AS name,
        b.category AS category,
        b.state AS state,
        b.availableCopies AS availableCopies
    FROM Book b
""")
    List<BookShortProjection> findAllShort();
    @Query("""
    SELECT 
        b.id AS id,
        b.name AS name,
        b.category AS category,
        b.state AS state,
        b.availableCopies AS availableCopies,
        a.name AS author_Name,
        a.surname AS author_Surname,
        c.name AS author_Country_Name
    FROM Book b
    JOIN b.author a
    JOIN a.country c
""")
    List<BookDetailsProjection> findAllDetailed();

    @EntityGraph(value = "Book.withAuthorAndCountry", type = EntityGraph.EntityGraphType.LOAD)
    List<Book> findAll();
    @Query(value = "SELECT * FROM book_view", nativeQuery = true)
    List<BookViewProjection> getBookView();

    @Query(value = "SELECT * FROM book_stats", nativeQuery = true)
    List<BookStatsProjection> getStats();
}
