#Tutorial 011

CREATE TABLE users5 (id INT PRIMARY KEY AUTO_INCREMENT, username TEXT, email TEXT);
INSERT INTO users5 (username, email) VALUES ("A", "lkjhgl");
INSERT INTO users5 (username, email) VALUES ("B", "ureyt");
INSERT INTO users5 (id, username, email) VALUES (0, "C", "qwed");

SELECT * FROM users5;