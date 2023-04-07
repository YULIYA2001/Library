-- DROP TABLE author, book, book_author, genre, item,
--     magazine, person_detail, person, person_item;

CREATE TABLE person
(
    id       BIGSERIAL PRIMARY KEY,
    email    VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL
);


CREATE TABLE person_detail
(
    person_id  BIGINT      NOT NULL PRIMARY KEY,
    surname    VARCHAR(30) NOT NULL,
    name       VARCHAR(30) NOT NULL,
    patronymic VARCHAR(30) DEFAULT '',
    phone      CHAR(17) UNIQUE,
    age        INT         NOT NULL CHECK (age > 6 AND age < 110),
    reg_date   TIMESTAMP   NOT NULL,
    FOREIGN KEY (person_id) REFERENCES person (id) ON DELETE CASCADE
);


CREATE TABLE item
(
    id       BIGSERIAL PRIMARY KEY,
    title    VARCHAR(100) NOT NULL,
    language VARCHAR(10)  NOT NULL,
--     is_available   BOOLEAN  NOT NULL,   -- и так видно person_id - NULL
    person_id BIGINT,
    FOREIGN KEY (person_id) REFERENCES person (id)
);

-- CREATE TABLE person_item
-- (
--     person_id BIGINT,
--     item_id   BIGINT,
--     PRIMARY KEY (person_id, item_id),
--     FOREIGN KEY (person_id) REFERENCES person (id),
--     FOREIGN KEY (item_id) REFERENCES item (id)
-- );


CREATE TABLE magazine
(
--     id      BIGSERIAL PRIMARY KEY,
    number  INT    NOT NULL,
    date    DATE   NOT NULL,
    theme   VARCHAR(100) DEFAULT '',
    item_id BIGINT NOT NULL UNIQUE,
    FOREIGN KEY (item_id) REFERENCES item (id) ON DELETE CASCADE
);


CREATE TABLE genre
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(50) NOT NULL,
    description VARCHAR(2000)
);


CREATE TABLE book
(
--     id       BIGSERIAL PRIMARY KEY,
    item_id  BIGINT NOT NULL UNIQUE,
    genre_id BIGINT,
    FOREIGN KEY (item_id) REFERENCES item (id) ON DELETE CASCADE,
    FOREIGN KEY (genre_id) REFERENCES genre (id)
);


CREATE TABLE author
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(90) NOT NULL,
    info VARCHAR(2000)
);


CREATE TABLE book_author
(
    book_id   BIGINT,
    author_id BIGINT,
    PRIMARY KEY (book_id, author_id),
    FOREIGN KEY (book_id) REFERENCES book (item_id) ON DELETE CASCADE ,
    FOREIGN KEY (author_id) REFERENCES author (id)
);
