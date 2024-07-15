-- Inserindo alguns autores iniciais
INSERT INTO author (name, birth_date, alive) VALUES ('Jane Austen', '1775-12-16', false);
INSERT INTO author (name, birth_date, alive) VALUES ('Mark Twain', '1835-11-30', false);
INSERT INTO author (name, birth_date, alive) VALUES ('Leo Tolstoy', '1828-09-09', false);

-- Inserindo alguns livros iniciais
INSERT INTO book (title, language, author_id) VALUES ('Pride and Prejudice', 'en', 1);
INSERT INTO book (title, language, author_id) VALUES ('Adventures of Huckleberry Finn', 'en', 2);
INSERT INTO book (title, language, author_id) VALUES ('War and Peace', 'ru', 3);
