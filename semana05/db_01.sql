CREATE DATABASE IF NOT EXISTS BDAula01;

SHOW DATABASES;

USE BDAula01;

CREATE TABLE pessoa (
	id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    sexo VARCHAR(1) NOT NULL,
    idioma VARCHAR(10) NOT NULL
);

SHOW TABLES;
DESC pessoa;

INSERT INTO pessoa (nome, sexo, idioma) 
VALUES
("Ricardo", "M", "portugues"),
("João", "M", "portugues"),
("Rafael", "M", "frances"),
("Joe", "M", "Espanhol"),
("Mary", "F", "ingles");

SELECT * FROM pessoa;


