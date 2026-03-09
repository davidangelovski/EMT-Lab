package com.example.emtlab.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="authors")
public class Author extends BaseAuditableEntity{
    private String name;

    private String surname;

    @ManyToOne
    private Country country;
}
