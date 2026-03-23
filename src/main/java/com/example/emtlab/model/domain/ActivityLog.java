package com.example.emtlab.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookName;
    private LocalDateTime timestamp;
    private String eventType;

    public ActivityLog(String name, LocalDateTime now, String borrow) {
        this.bookName = name;
        this.timestamp = now;
        this.eventType = borrow;
    }
}