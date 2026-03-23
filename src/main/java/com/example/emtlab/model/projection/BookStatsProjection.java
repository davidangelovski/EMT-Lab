package com.example.emtlab.model.projection;

public interface BookStatsProjection {
    String getCategory();
    Integer getTotalBooks();
    Integer getTotalAvailable();
    Integer getBadBooks();
}