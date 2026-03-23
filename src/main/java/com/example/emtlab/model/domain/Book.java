package com.example.emtlab.model.domain;

import com.example.emtlab.model.enums.Category;
import com.example.emtlab.model.enums.State;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="book")
@NamedEntityGraph(
        name = "Book.withAuthorAndCountry",
        attributeNodes = {
                @NamedAttributeNode(value = "author", subgraph = "author-subgraph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "author-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("country")
                        }
                )
        }
)
public class Book extends BaseAuditableEntity{
    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    private Author author;

    @Enumerated(EnumType.STRING)
    private State state;

    private Integer availableCopies;

    public Book(String name, Category category, Author author, State state, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.state = state;
        this.availableCopies = availableCopies;
    }
}
