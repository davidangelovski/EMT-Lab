package com.example.emtlab.web.controller;

import com.example.emtlab.model.domain.ActivityLog;
import com.example.emtlab.model.dto.CreateBookDto;
import com.example.emtlab.model.dto.DisplayBookDto;
import com.example.emtlab.model.enums.Category;
import com.example.emtlab.model.enums.State;
import com.example.emtlab.model.projection.BookDetailsProjection;
import com.example.emtlab.model.projection.BookShortProjection;
import com.example.emtlab.model.projection.BookStatsProjection;
import com.example.emtlab.model.projection.BookViewProjection;
import com.example.emtlab.repository.ActivityLogRepository;
import com.example.emtlab.service.application.BookApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookApplicationService bookApplicationService;

    private final ActivityLogRepository activityLogRepository;
    public BookController(BookApplicationService bookApplicationService, ActivityLogRepository activityLogRepository) {
        this.bookApplicationService = bookApplicationService;
        this.activityLogRepository = activityLogRepository;
    }

    @GetMapping
    public ResponseEntity<List<DisplayBookDto>> findAll() {
        return ResponseEntity.ok(bookApplicationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayBookDto> findById(@PathVariable Long id) {
        return bookApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DisplayBookDto> create(@RequestBody CreateBookDto createBookDto) {
        DisplayBookDto created = bookApplicationService.create(createBookDto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisplayBookDto> update(@PathVariable Long id,
                                       @RequestBody CreateBookDto createBookDto) {
        return bookApplicationService.update(id, createBookDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/borrow")
    public ResponseEntity<DisplayBookDto> borrow(@PathVariable Long id) {
        return bookApplicationService.borrowById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DisplayBookDto> delete(@PathVariable Long id) {
        return bookApplicationService.deleteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/search")
    public ResponseEntity<Page<DisplayBookDto>> search(
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) State state,
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) Boolean available,
            @PageableDefault(size = 10, sort = "name") Pageable pageable
    ) {
        return ResponseEntity.ok(
                bookApplicationService.findAllFiltered(category, state, authorId, available, pageable)
        );
    }
    @GetMapping("/short")
    public ResponseEntity<List<BookShortProjection>> getAllShort() {
        return ResponseEntity.ok(bookApplicationService.findAllShort());
    }
    @GetMapping("/details")
    public ResponseEntity<List<BookDetailsProjection>> getAllDetails() {
        return ResponseEntity.ok(bookApplicationService.findAllDetailed());
    }
    @GetMapping("/view")
    public ResponseEntity<List<BookViewProjection>> getView() {
        return ResponseEntity.ok(bookApplicationService.getBookView());
    }
    @GetMapping("/stats")
    public ResponseEntity<List<BookStatsProjection>> getStats() {
        return ResponseEntity.ok(bookApplicationService.getStats());
    }
    @GetMapping("/activities")
    public ResponseEntity<Page<ActivityLog>> getActivities(Pageable pageable) {
        return ResponseEntity.ok(activityLogRepository.findAll(pageable));
    }
}