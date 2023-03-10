--h2 is typically used to setup a test database, not a prod database.
--first, drop your tables (to reset your database for testing)
--then create your tables
DROP TABLE IF EXISTS store;

CREATE TABLE store(
    store_id int primary key auto_increment,
    store_name varchar(255),
    state varchar(255),
    zip int
);

-- Item Table

DROP TABLE IF EXISTS item;

-- NOTE: the primary key is a composite key, each store can have a similar item but at a different price, etc
-- TODO: make item.store_id reference store.store_id, removed for testing
CREATE TABLE IF NOT EXISTS item (
    id SERIAL,
    name VARCHAR(255),
    description VARCHAR(1024),
    price DECIMAL(10,2),
    store_id INTEGER NOT NULL,
    foreign key (store_id) references  store(store_id)
    PRIMARY KEY (id, store_id)
);