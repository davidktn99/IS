#Tutorial 006

CREATE TABLE users3 (id INT NOT NULL, username TEXT NOT NULL);
DESC users3;
#ERROR INSERT INTO users3 (id, username) VALUES (NULL, NULL);
#ERROR INSERT INTO users3 (id) VALUES (7);
#ERROR INSERT INTO users3 (username) VALUES ("Billy"); 