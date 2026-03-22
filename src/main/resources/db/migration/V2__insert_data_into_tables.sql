INSERT INTO country (name, continent)
VALUES ('United Kingdom', 'Europe'),
       ('United States', 'North America'),
       ('France', 'Europe');

INSERT INTO author (created_at, updated_at, name, surname, country_id)
VALUES (now(), now(), 'George', 'Orwell', 1),
       (now(), now(), 'Mark', 'Twain', 2),
       (now(), now(), 'Victor', 'Hugo', 3);

INSERT INTO book (created_at, updated_at, name, category, author_id, state, available_copies)
VALUES (now(), now(), '1984', 'NOVEL', 1, 'GOOD', 5),
       (now(), now(), 'Animal Farm', 'NOVEL', 1, 'GOOD', 3),
       (now(), now(), 'Adventures of Huckleberry Finn', 'NOVEL', 2, 'GOOD', 4),
       (now(), now(), 'Les Miserables', 'CLASSICS', 3, 'GOOD', 2),
       (now(), now(), 'The Idiot', 'DRAMA', 3, 'BAD', 1);