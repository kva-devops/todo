CREATE TABLE category (
                          id SERIAL PRIMARY KEY,
                          name TEXT
);
INSERT INTO category (name) VALUES ('Работа');
INSERT INTO category (name) VALUES ('Отдых');
INSERT INTO category (name) VALUES ('Семья');
INSERT INTO category (name) VALUES ('Путешествия');
INSERT INTO category (name) VALUES ('Друзья');
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name TEXT,
                       email TEXT,
                       password TEXT
);
CREATE TABLE items (
                       id SERIAL PRIMARY KEY,
                       description TEXT,
                       created TIMESTAMP,
                       done BOOLEAN,
                       user_id INT REFERENCES users(id)
);
CREATE TABLE items_category (
                                item_id INT NOT NULL,
                                categories_id INT NOT NULL
);