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