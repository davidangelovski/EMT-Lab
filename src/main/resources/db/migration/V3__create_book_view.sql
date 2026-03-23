CREATE VIEW book_view AS
SELECT
    b.id,
    b.name,
    b.category,
    b.state,
    b.available_copies,
    a.name || ' ' || a.surname AS author_full_name,
    c.name AS country_name
FROM book b
         JOIN author a ON b.author_id = a.id
         JOIN country c ON a.country_id = c.id;