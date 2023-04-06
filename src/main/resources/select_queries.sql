-- поиск по email и паролю
SELECT id
FROM person
WHERE email = 'bob@mail.ru'
  AND password = '1111';

-- информация о человеке по email и паролю
SELECT surname, name, patronymic, age, phone, reg_date
FROM person_detail
WHERE person_id = (SELECT (id)
                   FROM person
                   WHERE email = 'bob@mail.ru'
                     AND password = '1111');

-- информация о человеке по id
SELECT surname, name, patronymic, age, phone, reg_date
FROM person_detail
WHERE person_id = 1;

-- информация о журнале по id
SELECT id, title, language, is_available, person_id, number, date, theme
FROM item
         JOIN magazine ON item.id = magazine.item_id
WHERE id = 2;

-- доступные журналы
SELECT id, title, language, is_available, number, date, theme
FROM item
         JOIN magazine ON item.id = magazine.item_id
WHERE is_available = true;

-- доступные журналы на русском
SELECT id, title, language, is_available, number, date, theme
FROM item
         JOIN magazine ON item.id = magazine.item_id
WHERE is_available = true
  AND language = 'ru';

-- информация о книге
SELECT item.id,
       title,
       language,
       is_available,
       genre.name  AS genre,
       description AS genre_desc,
       author.name AS author,
       info        AS author_info
FROM item
         JOIN book ON item.id = book.item_id
         JOIN genre ON book.genre_id = genre.id
         JOIN book_author ON book.item_id = book_author.book_id
         JOIN author ON author.id = book_author.author_id
--WHERE author.id = 1;
--WHERE genre.id = 1;
--WHERE item.id = 1;
--WHERE author.name = 'А.С.Пушкин';
--WHERE genre.name = 'роман';
WHERE is_available = true
  AND item.id = 3;

-- информация об авторах книги с id=3
SELECT author.id, name AS author, info AS author_info --, item.id, title AS book
FROM author
         JOIN book_author ON author.id = book_author.author_id
         JOIN book ON book_author.book_id = book.item_id
         JOIN item ON book.item_id = item.id
WHERE item.id = 3;


-- информация о взятых человеком книгах
SELECT item.id,
       title,
       language,
       genre.name  as genre,
       description AS genre_desc,
       author.name AS author,
       info        AS author_info
FROM item
         JOIN book ON item.id = book.item_id
         JOIN genre ON book.genre_id = genre.id
         JOIN book_author ON book.item_id = book_author.book_id
         JOIN author ON author.id = book_author.author_id
WHERE person_id = 1;

-- информация о взятых человеком журналах
SELECT item.id, title, language, number, date, theme
FROM item
         JOIN magazine ON item.id = magazine.item_id
WHERE person_id = 1;


-- информация о жанрах
SELECT id, name, description
FROM genre;
-- WHERE id = 1;