CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name TEXT,
    email TEXT,
    password TEXT
);

CREATE TABLE items (
    id SERIAL PRIMARY KEY,
    description TEXT,
    done BOOLEAN,
    user_id INT REFERENCES users(id)
);