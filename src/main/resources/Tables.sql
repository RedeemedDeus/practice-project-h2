--h2 is typically used to setup a test database, not a prod database.
--first, drop your tables (to reset your database for testing)
--then create your tables


-- Item Table

DROP TABLE IF EXISTS item;

CREATE TABLE IF NOT EXISTS item (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    price DECIMAL(10,2),
    description VARCHAR(1024),
    store_id INTEGER NOT NULL
);