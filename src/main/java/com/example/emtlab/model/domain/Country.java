package com.example.emtlab.model.domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Country extends BaseEntity{
    private String name;

    private String continent;
}
