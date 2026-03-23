package com.example.emtlab.listeners;

import com.example.emtlab.events.BookBorrowedEvent;
import com.example.emtlab.model.domain.ActivityLog;
import com.example.emtlab.model.domain.Book;
import com.example.emtlab.repository.ActivityLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.LocalDateTime;

@Component
@Slf4j
public class BookEventListener {
    private final ActivityLogRepository activityLogRepository;

    public BookEventListener(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handleBorrow(BookBorrowedEvent event) {
        Book book = event.getBook();

        log.info("Book borrowed: " + book.getName());
        ActivityLog activityLog = new ActivityLog();
        activityLogRepository.save(
                new ActivityLog(book.getName(), LocalDateTime.now(), "BORROW")
        );
        if (book.getAvailableCopies() == 0) {
            System.out.println("Book is now unavailable!");
        }
    }
}