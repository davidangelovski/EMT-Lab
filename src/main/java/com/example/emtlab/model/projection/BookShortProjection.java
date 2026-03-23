package com.example.emtlab.model.projection;

import com.example.emtlab.model.enums.Category;
import com.example.emtlab.model.enums.State;

public interface BookShortProjection {
    Long getId();
    String getName();
    Category getCategory();
    State getState();
    Integer getAvailableCopies();
}
