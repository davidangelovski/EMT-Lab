CREATE MATERIALIZED VIEW book_stats AS
SELECT
    category,
    COUNT(*) AS total_books,
    SUM(available_copies) AS total_available,
    SUM(CASE WHEN state = 'BAD' THEN 1 ELSE 0 END) AS bad_books
FROM book
GROUP BY category;