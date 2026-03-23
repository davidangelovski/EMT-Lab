package com.example.emtlab.repository.specification;

import com.example.emtlab.model.domain.Book;
import com.example.emtlab.model.enums.Category;
import com.example.emtlab.model.enums.State;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BookSpecification {

    public static Specification<Book> filter(
            Category category,
            State state,
            Long authorId,
            Boolean available
    ) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (category != null)
                predicates.add(cb.equal(root.get("category"), category));

            if (state != null)
                predicates.add(cb.equal(root.get("state"), state));

            if (authorId != null)
                predicates.add(cb.equal(root.get("author").get("id"), authorId));

            if (available != null) {
                if (available)
                    predicates.add(cb.greaterThan(root.get("availableCopies"), 0));
                else
                    predicates.add(cb.equal(root.get("availableCopies"), 0));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
