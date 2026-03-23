package com.example.emtlab.model.projection;

public interface BookViewProjection {
    Long getId();
    String getName();
    String getCategory();
    String getState();
    Integer getAvailableCopies();
    String getAuthorFullName();
    String getCountryName();
}