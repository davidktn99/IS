#Tutorial 005

CREATE TABLE users2 (id INT, username TEXT);
SHOW TABLES;

INSERT INTO users2 (id, username) VALUES (1, "Lenny");
INSERT INTO users2 (id, username) VALUES (2, "Timmy");
INSERT INTO users2 (id, username) VALUES (NULL, NULL);
INSERT INTO users2 (id) VALUES (3);

SELECT * FROM users2;
DROP TABLE users2;
SHOW TABLES;