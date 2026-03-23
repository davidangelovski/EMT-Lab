package com.example.emtlab.model.projection;

import com.example.emtlab.model.enums.Category;
import com.example.emtlab.model.enums.State;

public interface BookDetailsProjection {
    Long getId();
    String getName();
    Category getCategory();
    State getState();
    Integer getAvailableCopies();

    String getAuthor_Name();
    String getAuthor_Surname();
    String getAuthor_Country_Name();
}