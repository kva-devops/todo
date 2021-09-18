CREATE TABLE items (
    id SERIAL PRIMARY KEY,
    description TEXT,
    created TIMESTAMP,
    done BOOLEAN
);