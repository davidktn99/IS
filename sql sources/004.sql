DROP DATABASE IF EXISTS davidDB;
CREATE DATABASE davidDB;
USE davidDB;

CREATE TABLE users (
    username TEXT
);
SHOW TABLES;
DESC users;
INSERT INTO users (username) VALUES ("John");
INSERT INTO users (username) VALUES ("Bob");
SELECT * FROM users;
