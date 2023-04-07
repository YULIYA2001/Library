INSERT INTO person(email, password)
VALUES ('bob@mail.ru', '1111');

INSERT INTO person_detail
VALUES (1, 'smith', 'bob', '', '+375(29)123-45-67', 50, '2023-03-31');

INSERT INTO author(name, info)
VALUES ('А.С. Пушкин', '1799-1837 Русский поэт, драматург и прозаик.');

INSERT INTO genre(name, description)
VALUES ('роман',
        'литературный жанр, чаще прозаический, зародившийся в средние века у романских народов как рассказ на народном языке');

INSERT INTO item(title, language)
VALUES ('Евгений Онегин', 'ru');

INSERT INTO book
VALUES (1, 1);

INSERT INTO book_author
VALUES (1, 1);

INSERT INTO item(title, language)
VALUES ('Фитиль', 'ru');

INSERT INTO magazine
VALUES (5, '1998-03-15', 'юмор', 2);



INSERT INTO author(name, info)
VALUES ('Е.П. Петров', '1902-1942 Русский советский писатель, сценарист...');

INSERT INTO author(name, info)
VALUES ('И.А. Ильф', '1897-1937 Русский советский писатель, драматург...');

INSERT INTO item(title, language)
VALUES ('12 стульев', 'ru');

INSERT INTO book
VALUES (3, 1);

INSERT INTO book_author
VALUES (3, 2);

INSERT INTO book_author
VALUES (3, 3);


UPDATE item
SET person_id = 1
WHERE id = 3;


-- -- функция и триггер, изменяющие доступность книги на "недоступна"
-- -- при появлении связи "человек-книга" (человек взял книгу)
-- CREATE OR REPLACE FUNCTION update_availability()
--     RETURNS trigger AS
-- $$
-- BEGIN
--     UPDATE item
--     SET is_availible = false
--     WHERE id = NEW.item_id;
--     RETURN NEW;
-- END;
-- $$
--     LANGUAGE 'plpgsql';
--
-- CREATE OR REPLACE TRIGGER person_item_insert
--     AFTER INSERT
--     ON person_item
--     FOR EACH ROW
-- EXECUTE FUNCTION update_availability();
--
-- DROP TRIGGER person_item_insert ON person_item;
-- DROP FUNCTION update_availability();




INSERT INTO genre(name, description)
VALUES ('сказка',
        'один из жанров фольклора либо художественной литературы');

INSERT INTO genre(name, description)
VALUES ('рассказ',
        ' основной жанр малой повествовательной прозы');

INSERT INTO genre(name, description)
VALUES ('повесть',
        'прозаический жанр, по объёму текста между романом и рассказом');





-- DELETE FROM genre WHERE id>4;
DELETE FROM book_author WHERE book_id>3;
DELETE FROM book WHERE item_id>3;
DELETE FROM item WHERE id>3;
